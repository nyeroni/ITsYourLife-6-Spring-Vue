package org.scoula.board.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.common.util.UploadFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService service;

    @GetMapping("")
    public ResponseEntity<Page> getList(PageRequest pageRequest) {
        return ResponseEntity.ok(service.getPage(pageRequest));
    }
//    @GetMapping("")
//    public ResponseEntity<List<BoardDTO>> getList() {
//        return ResponseEntity.ok(service.getList());
//    }

    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(@PathVariable("no") Long no) {
        return ResponseEntity.ok(service.get(no));
    }

    @PostMapping("")
    public ResponseEntity<BoardDTO> create(BoardDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(@PathVariable("no") Long no, BoardDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable("no") Long no) {
        return ResponseEntity.ok(service.delete(no));
    }

    @GetMapping("/download/{no}")
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {
        BoardAttachmentVO attachmentVO = service.getAttachment(no);
        File file = new File(attachmentVO.getPath());
        UploadFiles.download(response, file, attachmentVO.getFilename());
    }

    @DeleteMapping("/deleteAttachment/{no}")
    public ResponseEntity<Boolean> deleteAttachment(@PathVariable("no") Long no) throws Exception {
        return ResponseEntity.ok(service.deleteAttachment(no));
    }

}
