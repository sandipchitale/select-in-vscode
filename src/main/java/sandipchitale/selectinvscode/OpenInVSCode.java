package sandipchitale.selectinvscode;

import com.intellij.execution.Platform;
import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.nio.file.Path;

public class OpenInVSCode implements SelectInTarget {
    @Override
    public boolean canSelect(SelectInContext context) {
        return context.getVirtualFile().isInLocalFileSystem();
    }

    @Override
    public void selectIn(SelectInContext context, boolean requestFocus) {
        VirtualFile virtualFile = context.getVirtualFile();
        if (virtualFile.isInLocalFileSystem()) {
            try {
                String codeExecutable = Platform.current().equals(Platform.WINDOWS) ? "code.cmd" : "code";
                Process process = new ProcessBuilder()
                        .command(codeExecutable,
                                virtualFile.getPath().replace("/", "\\")
                        ).inheritIO().start();
                new Thread(() -> {
                    try {
                        process.waitFor();
                    } catch (InterruptedException ignore) {
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public float getWeight() {
        return 1000;
    }

    @Override
    public String toString() {
        return "Open in VS Code...";
    }
}
