package sandipchitale.selectinvscode;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class OpenInVSCodeAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getDataContext().getData(CommonDataKeys.EDITOR);
        VirtualFile virtualFile = e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE);
        if (editor == null) {
            VSCodeService.openInVSCode(virtualFile);
        } else {
            LogicalPosition logicalPosition = editor.getCaretModel().getLogicalPosition();
            VSCodeService.openInVSCode(virtualFile, logicalPosition.line, logicalPosition.column);
        }
    }
}
