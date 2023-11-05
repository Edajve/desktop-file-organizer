package org.example.src.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.operations.flagOperations.Delete;
import org.example.src.operations.flagOperations.Move;
import org.example.src.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileOperations {
    final private Delete delete;
    final private Utility utility;
    final private Move move;
    private static final Logger logger = LogManager.getLogger(FileOperations.class);
    public FileOperations() {
        this.utility = null;
        this.move = new Move();
        this.delete = new Delete();
    }

    public FileOperations(Utility utility) {
        this.utility = utility;
        this.move = new Move();
        this.delete = new Delete();
    }

    public void processFilesBasedOnCriteria(List<File> files) throws IOException {

        for (File file : files) {
            String keyword = this.utility.extractKeywordFromFileName(file);

            if ("trash".equals(keyword)) {
                this.delete.deleteFile(file);
            } else {
                this.move.moveFileToDestination(file, keyword);
            }
        }
    }
}
