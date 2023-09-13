package sandipchitale.selectinvscode;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.jetbrains.annotations.NotNull;

public class OpenInVSCodeAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        VSCodeService.openInVSCode(e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE));
    }
}
