import UIKit
import SwiftUI
import shared
import otherPod

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let resourcePath = Bundle.main.resourcePath!
        do {
            let resourceFiles = try FileManager.default.contentsOfDirectory(atPath: resourcePath)
            print("Resources: \(resourceFiles)")
        } catch let error as NSError {
            print("Error: \(error)")
        }
        return Main_iosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



