package http.controllers;

import http.IO.file.FileIO;
import http.router.Router;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostFileContentsControllerTest {

    private PostFileContentsController postFileContentsController;

    @BeforeEach
    void setUp() {
        FileIO fileIO = new FileIO("./public");
        Router router = new Router();
        postFileContentsController = new PostFileContentsController(router, fileIO);
    }

    @Test
    void generateResponse() {
    }
}