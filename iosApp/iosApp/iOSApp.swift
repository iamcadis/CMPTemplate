import SwiftUI
import FirebaseCore

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {

        registerFirebaseApp()
        return true
    }

    private func registerFirebaseApp() {
        #if DEBUG
        let fileName = "GoogleService-Info-Debug"
        #else
        let fileName = "GoogleService-Info-Release"
        #endif

        guard let filePath = Bundle.main.path(forResource: fileName, ofType: "plist"),
              let fileOptions = FirebaseOptions(contentsOfFile: filePath)
        else {
            fatalError("Couldn't load firebase config file.")
        }
        FirebaseApp.configure(options: fileOptions)
    }
}
