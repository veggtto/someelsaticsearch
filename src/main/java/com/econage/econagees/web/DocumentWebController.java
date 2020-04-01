package com.econage.econagees.web;

import com.econage.econagees.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/document")
public class DocumentWebController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("")
    public Object getDocument() throws IOException {
        return documentService.getDocument();
    }

    @PostMapping("")
    public Object createDocument() throws IOException {
        return documentService.addDocument();
    }

    @PutMapping("")
    public Object updateDocument() throws IOException {
        return documentService.updateDocument();
    }

    @DeleteMapping("")
    public Object deleteDocument(@RequestParam("id") String id) throws IOException {
        return documentService.deleteDocument(id);
    }

}

