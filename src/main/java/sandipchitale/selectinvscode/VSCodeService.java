package sandipchitale.selectinvscode;

import com.intellij.execution.Platform;
import com.intellij.openapi.ui.messages.MessageDialog;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

public class VSCodeService {
    static void openInVSCode(VirtualFile virtualFile) {
        if (virtualFile != null && virtualFile.isInLocalFileSystem()) {
            try {
                String codeExecutable;
                String virtualFilePath;
                if (Platform.current().equals(Platform.WINDOWS)) {
                    codeExecutable = "code.cmd";
                    virtualFilePath = virtualFile.getPath().replace("/", "\\");
                } else {
                    codeExecutable = "code";
                    virtualFilePath = virtualFile.getPath();
                }
                Process process = new ProcessBuilder()
                        .command(codeExecutable, virtualFilePath)
                        .inheritIO().start();
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
}
