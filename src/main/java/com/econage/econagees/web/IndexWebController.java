package com.econage.econagees.web;

import com.econage.econagees.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class IndexWebController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/exits")
    public Object exitsIndex() throws IOException {
        return indexService.indexExist();
    }

    @PostMapping("")
    public Object createIndex() throws IOException {
        return indexService.createIndex();
    }

    @DeleteMapping("")
    public Object deleteIndex() throws IOException {
        return indexService.deleteIndex();
    }
}

