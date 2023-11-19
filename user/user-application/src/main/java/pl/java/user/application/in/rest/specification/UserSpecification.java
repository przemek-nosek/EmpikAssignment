package pl.java.user.application.in.rest.specification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pl.java.user.application.in.response.UserResponse;

public interface UserSpecification {

    @Operation(
            summary = "Gets user by login.",
            description = "Gets user by login and returns data with calculations. If failed and exception is thrown.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User returned successfully", content = @Content(
                            schema = @Schema(implementation = UserResponse.class),
                            examples = {
                                    @ExampleObject(name = "exampleResponse", value = """
                                                 {
                                                 "id": 10000,
                                                 "login": "user",
                                                 "name": "Jan Nowak",
                                                 "type": "User",
                                                 "avatarUrl": "https://avatars.githubusercontent.com/u/12345",
                                                 "createdAt": "2015-10-10T06:56:09",
                                                 "calculations": 0.9069767441860465
                                                 }
                                            """)
                            }
                    )),
                    @ApiResponse(responseCode = "404", description = "User not found in GitHub API.", content = @Content(
                            examples = {
                                    @ExampleObject(name = "exampleNotFoundError", value = """
                                                {
                                                "httpStatus": "NOT_FOUND",
                                                "statusCode": 404,
                                                "timeStamp": "2023-11-17T10:18:44.7281653",
                                                "message": "URL https://api.github.com/users/user_not_found not found."
                                                }
                                            """)
                            }
                    )),
                    @ApiResponse(responseCode = "422", description = "User's follower count is zero and can't divide.", content = @Content(
                            examples = {
                                    @ExampleObject(name = "exampleUnprocessable_entity", value = """
                                                {
                                                "httpStatus": "UNPROCESSABLE_ENTITY",
                                                "statusCode": 422,
                                                "timeStamp": "2023-11-17T10:27:41.3982729",
                                                "message": "User's przemek-nosek follower count is zero therefore can not divide."
                                                }
                                            """)
                            }
                    )),
            }
    )
    ResponseEntity<UserResponse> getUser(
            @Parameter(
                    in = ParameterIn.PATH,
                    name = "login",
                    description = "login of a user",
                    required = true,
                    example = "user"
            )
            @PathVariable String login);
}
