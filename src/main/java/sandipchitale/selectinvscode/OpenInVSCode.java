package sandipchitale.selectinvscode;

import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorImpl;

import java.util.Objects;

public class OpenInVSCode implements SelectInTarget {
    @Override
    public boolean canSelect(SelectInContext context) {
        return context.getVirtualFile().isInLocalFileSystem();
    }

    @Override
    public void selectIn(SelectInContext context, boolean requestFocus) {
        FileEditor fileEditor = Objects.requireNonNull(context.getFileEditorProvider()).openFileEditor();
        if (fileEditor instanceof PsiAwareTextEditorImpl psiAwareTextEditor) {
            VSCodeService.openInVSCode(context.getVirtualFile(),
                    psiAwareTextEditor.getEditor().getCaretModel().getLogicalPosition().line,
                    psiAwareTextEditor.getEditor().getCaretModel().getLogicalPosition().column);
            return;
        }
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
