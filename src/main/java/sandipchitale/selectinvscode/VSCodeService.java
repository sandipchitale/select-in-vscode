package sandipchitale.selectinvscode;

import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.lang.SystemUtils;

import java.io.IOException;

public class VSCodeService {
    static void openInVSCode(VirtualFile virtualFile) {
        openInVSCode(virtualFile, -1, -1);
    }

    static void openInVSCode(VirtualFile virtualFile, int line, int column) {
        if (virtualFile != null && virtualFile.isInLocalFileSystem()) {
            try {
                String codeExecutable = "code";
                String virtualFilePath = virtualFile.getPath();

                if (SystemUtils.IS_OS_MAC) {
                    codeExecutable = "/Applications/Visual Studio Code.app/Contents/Resources/app/bin/code";
                } else if (SystemUtils.IS_OS_WINDOWS) {
                    codeExecutable = "code.cmd";
                    virtualFilePath = virtualFilePath.replace("/", "\\");
                }
                if (line > -1) {
                    virtualFilePath += (":" + (line+1) + ((column > -1 ? ":" + (column+1) : "")));
                }
                Process process = new ProcessBuilder()
                        .command(codeExecutable,
                                "-g",
                                virtualFilePath)
                        .inheritIO()
                        .start();
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
