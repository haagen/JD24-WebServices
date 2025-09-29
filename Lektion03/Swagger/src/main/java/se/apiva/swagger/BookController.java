package se.apiva.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Operation(
            summary = "Get all books",
            description = "Returns a list of all available books"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No books found",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @GetMapping
    public List<Book> getBooks() {
        return List.of(
          new Book(1, "Effective Java"),
          new Book(2, "Discover Spring Framework")
        );
    }

}
