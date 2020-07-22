package sudoku.web;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import sudoku.domain.SudokuBoard;
import sudoku.output.CliOutput;
import sudoku.output.SudokuOutput;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.Random;

@Controller("/sudoku")
public class SudokuController {

    private final SudokuService service;
    private final Random random = new Random();

    public SudokuController(SudokuService service) {
        this.service = service;
    }

    @Get("/random.{fmt}")
    public HttpResponse<?> randomBoard(String fmt) {
        long seed = random.nextInt(Integer.MAX_VALUE);
        return HttpResponse.redirect(URI.create("/sudoku/puzzle/" +seed + '.' + fmt));
    }

    @Get("/puzzle/{seed}.{fmt}")
    public HttpResponse<?> puzzle(long seed, String fmt) throws IOException {
        SudokuBoard puzzle = service.generateBoards(seed).puzzle;
        return render(fmt, puzzle);
    }

    @Get("/solution/{seed}.{fmt}")
    public HttpResponse<?> solution(long seed, String fmt) throws IOException {
        SudokuBoard solution = service.generateBoards(seed).solution;
        return render(fmt, solution);
    }

    HttpResponse<?> render(String fmt, SudokuBoard board) throws IOException {
        Object body;
        SudokuOutput output;
        if (fmt.equals("txt")) {
            StringWriter out = new StringWriter(2048);
            output = new CliOutput();
            output.render(board, out);
            body = out.toString();
        } else if (fmt.equals("png")) {
            body = "";
        } else if (fmt.equals("json")) {
            body = board;
        } else {
            return HttpResponse.badRequest("bad format: " + fmt);
        }

        return HttpResponse.ok(body)
                .header("X-My-Header", "Foo");
    }
}
