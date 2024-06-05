import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIModalPresentationFormSheet
import platform.UIKit.UISheetPresentationControllerDetent
import platform.UIKit.UIViewController
import platform.UIKit.sheetPresentationController
import platform.Foundation.NSLog

fun MainViewController(): UIViewController = ComposeUIViewController {
    NSLog("Creating bottom sheet VC")
    val bottomSheetUIViewController =
        ComposeUIViewController {
            VerticalScrollWithIndependentHorizontalRows()
        }
    NSLog("Setting presentation style")
    bottomSheetUIViewController.modalPresentationStyle = UIModalPresentationFormSheet
    NSLog("Setting detents")
    bottomSheetUIViewController.sheetPresentationController?.setDetents(
        listOf(
            UISheetPresentationControllerDetent.mediumDetent(),
            UISheetPresentationControllerDetent.largeDetent(),
        )
    )
    NSLog("Before Presenting View Controller")
    UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
        viewControllerToPresent = bottomSheetUIViewController,
        animated = true,
        completion = {
            NSLog("Presenting View Controller")
        },
    )
}

@Composable
fun VerticalScrollWithIndependentHorizontalRows() {
    val verticalScrollState = rememberScrollState()
    NSLog("Scrollable Column")
    Column(
        modifier =
        Modifier.fillMaxSize().verticalScroll(verticalScrollState, enabled = true),
    ) {
        repeat(10) { rowIndex ->
            val horizontalScrollState = rememberScrollState()
            NSLog("Scrollable rows")
            Spacer(Modifier.height(30.dp).background(Color.DarkGray))
            Row(
                modifier =
                Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .horizontalScroll(horizontalScrollState),
            ) {
                repeat(5) {
                    Box(
                        modifier =
                        Modifier
                            .size(100.dp)
                            .background(Color.Gray),
                    ) {
                        Text("Item $it in row $rowIndex")
                    }
                }
            }
        }
    }
}
