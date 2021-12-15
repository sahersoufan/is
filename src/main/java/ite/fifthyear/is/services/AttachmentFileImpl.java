package ite.fifthyear.is.services;

import ite.fifthyear.is.domain.AttachmentFile;
import ite.fifthyear.is.repository.AttachmentFileRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class AttachmentFileImpl implements AttachmentFileService {
    private final AttachmentFileRepo attachmentFileRepo;

    @Override
    public void saveNewFile(AttachmentFile attachmentFile) {

    }

    @Override
    public void updateFile(AttachmentFile attachmentFile) {

    }

    @Override
    public void deleteFile(Long id) {

    }

    @Override
    public void deleteFile(String username) {

    }

    @Override
    public AttachmentFile getFile(Long id) {
        return null;
    }

    @Override
    public AttachmentFile getFile(String username) {
        return null;
    }
}
