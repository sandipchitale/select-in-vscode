package sandipchitale.selectinvscode;

import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;

public class OpenInVSCode implements SelectInTarget {
    @Override
    public boolean canSelect(SelectInContext context) {
        return context.getVirtualFile().isInLocalFileSystem();
    }

    @Override
    public void selectIn(SelectInContext context, boolean requestFocus) {
        VSCodeService.openInVSCode(context.getVirtualFile());
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
