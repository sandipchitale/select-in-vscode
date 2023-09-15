package sandipchitale.selectinvscode;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

public class OpenInVSCodeAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getDataContext().getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            VSCodeService.openInVSCode(e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE));
        } else {
            VSCodeService.openInVSCode(e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE),
                    editor.getCaretModel().getLogicalPosition().line,
                    editor.getCaretModel().getLogicalPosition().column);
        }
    }
}
