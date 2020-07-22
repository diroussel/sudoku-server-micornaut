package sudoku.output;

import sudoku.domain.SudokuBoard;

import java.util.function.Consumer;

public class SvgOutput implements SudokuOutput {
    @Override
    public void render(SudokuBoard board, Consumer<String> writer) {
        String header = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
                "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3" +
                ".org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
                "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3" +
                ".org/1999/xlink\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" " +
                "xmlns:cc=\"http://web.resource.org/cc/\" xmlns:rdf=\"http://www.w3" +
                ".org/1999/02/22-rdf-syntax-ns#\" xmlns:sodipodi=\"http://sodipodi.sourceforge" +
                ".net/DTD/sodipodi-0.dtd\" xmlns:inkscape=\"http://www.inkscape" +
                ".org/namespaces/inkscape\" version=\"1.1\" baseProfile=\"full\" width=\"600px\" " +
                "height=\"600px\" viewBox=\"0 0 600 600\" preserveAspectRatio=\"xMidYMid meet\" " +
                "id=\"svg_document\" style=\"zoom: 1;\">";

        writer.accept(header);

        // Horizontal lines
//        IntStream.range(0, 9).forEach(i -> {
//
//            writer.accept("<line stroke=\"#000000\" y1=\"50\"  x1=\"50\" stroke-width=\"3px\" " +
//                    "y2=\"50\" x2=\"500\"></line>\n");
//        });

        writer.accept("<line stroke=\"#000000\" y1=\"50\"  x1=\"50\" stroke-width=\"3px\" " +
                "y2=\"50\" x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"100\" x1=\"50\" stroke-width=\"1px\" y2=\"100\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"150\" x1=\"50\" stroke-width=\"1px\" y2=\"150\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"200\" x1=\"50\" stroke-width=\"3px\" y2=\"200\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"250\" x1=\"50\" stroke-width=\"1px\" y2=\"250\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"300\" x1=\"50\" stroke-width=\"1px\" y2=\"300\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"350\" x1=\"50\" stroke-width=\"3px\" y2=\"350\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"400\" x1=\"50\" stroke-width=\"1px\" y2=\"400\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"450\" x1=\"50\" stroke-width=\"1px\" y2=\"450\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" y1=\"500\" x1=\"50\" stroke-width=\"3px\" y2=\"500\" " +
                "x2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"50\"  y1=\"50\" stroke-width=\"3px\" x2=\"50\"  " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"100\" y1=\"50\" stroke-width=\"1px\" x2=\"100\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"150\" y1=\"50\" stroke-width=\"1px\" x2=\"150\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"200\" y1=\"50\" stroke-width=\"3px\" x2=\"200\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"250\" y1=\"50\" stroke-width=\"1px\" x2=\"250\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"300\" y1=\"50\" stroke-width=\"1px\" x2=\"300\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"350\" y1=\"50\" stroke-width=\"3px\" x2=\"350\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"400\" y1=\"50\" stroke-width=\"1px\" x2=\"400\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"450\" y1=\"50\" stroke-width=\"1px\" x2=\"450\" " +
                "y2=\"500\"></line>\n" +
                "<line stroke=\"#000000\" x1=\"500\" y1=\"50\" stroke-width=\"3px\" x2=\"500\" " +
                "y2=\"500\"></line>");

        for (int i = 0; i < board.getRows().size(); i++) {
            int[] cols = board.getRows().get(i);
            for (int j = 0; j < cols.length; j++) {
                int x = 25 + (i + 1) * 50;
                int y = 35 + (j + 1) * 50;
                int value = cols[j];
                if (value > 0) {
                    writer.accept("<text x=\"" + x + "px\" font-family=\"Helvetica\" " +
                            "font-size=\"32px\" y=\"" + y + "px\" text-anchor=\"middle\">" + value +
                            "</text>\n");
                }
            }
        }
        writer.accept("</svg>");
    }
}
