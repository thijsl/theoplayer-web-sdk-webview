//
//  ViewController.swift
//  iOS
//
//  Created by Thijs on 03/02/2022.
//

import UIKit
import WebKit

class ViewController: UIViewController, WKNavigationDelegate {
    
    var webView: WKWebView!

    override func viewDidLoad() {
        super.viewDidLoad()
        let webViewConfiguration = WKWebViewConfiguration()
        webViewConfiguration.allowsInlineMediaPlayback = true
        webViewConfiguration.allowsAirPlayForMediaPlayback = true
        webViewConfiguration.allowsPictureInPictureMediaPlayback = true
        webView = WKWebView(frame: CGRect(), configuration: webViewConfiguration)
        webView.navigationDelegate = self
        view = webView
        let url = Bundle.main.url(forResource: "app", withExtension: "html")
        webView.load(URLRequest(url: url!))
        webView.allowsBackForwardNavigationGestures = true
        let reloadStream = true
        if (reloadStream) {
            webView.evaluateJavaScript("reloadStream(true)")
        }
    }


}

