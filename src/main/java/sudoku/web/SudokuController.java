package sudoku.web;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import sudoku.domain.SudokuBoard;
import sudoku.output.CliOutput;
import sudoku.output.SudokuOutput;
import sudoku.output.SvgOutput;

import java.io.StringWriter;
import java.net.URI;
import java.util.Random;

import static io.micronaut.http.HttpResponse.ok;

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
        return HttpResponse.temporaryRedirect(URI.create("/sudoku/puzzle/" + seed + '.' + fmt));
    }

    @Get("/puzzle/{seed}.{fmt}")
    public HttpResponse<?> puzzle(long seed, String fmt) {
        SudokuBoard puzzle = service.generateBoards(seed).puzzle;
        return render(fmt, puzzle);
    }

    @Get("/solution/{seed}.{fmt}")
    public HttpResponse<?> solution(long seed, String fmt) {
        SudokuBoard solution = service.generateBoards(seed).solution;
        return render(fmt, solution);
    }

    HttpResponse<?> render(String fmt, SudokuBoard board) {
        switch (fmt) {
            case "txt":
                return ok(render(board, new CliOutput()))
                        .contentType(MediaType.TEXT_PLAIN);
            case "svg":
                return ok(render(board, new SvgOutput()))
                        .contentType("image/svg+xml");
            case "json":
                return ok(board)
                        .contentType(MediaType.APPLICATION_JSON_TYPE);
            default:
                return HttpResponse.badRequest("bad format: " + fmt);
        }
    }

    private Object render(SudokuBoard board, SudokuOutput output) {
        StringWriter out = new StringWriter(8 * 1024);
        output.render(board, out::write);
        return out.toString();
    }
}
