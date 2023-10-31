package org.example.src.operations.flagOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.File;
import java.util.List;

public class Delete {

    private static final Logger logger = LogManager.getLogger(Delete.class);

    final private FileOperations fileOperations;
    final private Desktop desktop;
    final private List<String> arguments;

    public Delete(List<String> arguments) {
        this.arguments = arguments;
        this.fileOperations = new FileOperations();
        this.desktop = new Desktop(this.fileOperations);
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public void deleteIndexFromArguments(int index) {
        this.arguments.remove(index);
    }

    private void clear() {
        getArguments().clear();
    }

    public void execute() {
        List<String> filesToDelete = getArguments().subList(1, getArguments().size());
        for (String fileName : filesToDelete) {
            String path = this.desktop.getPath(fileName);
            this.fileOperations.deleteFile(new File(path));
        }
        clear();
    }
}
