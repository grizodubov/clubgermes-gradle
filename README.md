# clubgermes-gradle
tauri mobile project config
**Tauri News:** **[Mobile Support](https://tauri.app/blog/2023/03/01/create-tauri-app-version-3-released/#tauri-20-alpha--mobile-support)**

[Gradle](https://www.notion.so/Gradle-a1cf7cfae5b1421a908abc736b3a7fac?pvs=21) 

[progress gradle automation](https://www.notion.so/progress-gradle-automation-a9579b7acea94125b5abd249b0b132fe?pvs=21)

```bash
git clone git@bitbucket.org:jazz-sundog/club-client.git ~/git/clubgermes
cd ~/git/clubgermes/
#nvm install node что-то не так с нодой
#сразу поменял dependencies tauri api и cli на "next", чтобы ^2.0.0-alpha
npm install
```

[Setting Up macOS | Tauri Apps](https://next--tauri.netlify.app/next/guides/getting-started/prerequisites/macos/)

```bash
	rustup self uninstall

curl --proto '=https' --tlsv1.2 https://sh.rustup.rs -sSf | sh #install Rust on macOS:
exec zsh -l #restart terminal
rustup target add aarch64-linux-android armv7-linux-androideabi i686-linux-android x86_64-linux-android #install the required rust android targets
#Set the JAVA_HOME environment variable to the location of the JDK:
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home" #у меня /Users/valeriigrizodubov/Library/Application Support/JetBrains/Toolbox/apps/AndroidStudio/ch-0/221.6008.13.2211.9619390/Android Studio.app/Contents/jbr/Contents/Home
#Install the Android SDK and NDK. And add environment variables:
export ANDROID_HOME="$HOME/Library/Android/sdk"
export NDK_HOME="$ANDROID_HOME/ndk/25.2.9519653" #Note ... version of NDK you installed.
rustup target add aarch64-apple-ios x86_64-apple-ios aarch64-apple-ios-sim #install the required rust iOS targets

	rustup update
```

[Configuration | Tauri Apps](https://next--tauri.netlify.app/next/mobile/development/configuration)

<aside>
💡 Installing the CLI through Cargo is the preferred option.
Alpha builds are updated regularly while this documentation may fall behind. To see the newest version numbers, check the [**Tauri Releases on GitHub**](https://github.com/tauri-apps/tauri/releases). ***[Tauri Repositories](https://github.com/tauri-apps/tauri)***

</aside>

```bash
npm install @tauri-apps/api@next
cargo install tauri-cli --version "^2.0.0-alpha"
cd ~/git/clubgermes/src-tauri/ #Update [**Cargo Packages**](https://crates.io/crates/tauri-build/versions)
cargo add tauri@2.0.0-alpha.10
cargo add tauri-build@2.0.0-alpha.6 --build
cargo update
cd ../
//To develop mobile Tauri applications, your frontend must serve the assets listening on your public network address. The network address can be found using the [**internal-ip NPM**](https://www.npmjs.com/package/internal-ip?activeTab=readme):
npm install --save-dev internal-ip

	cargo tauri info
	cargo tauri dev -- --verbose
```

- [project]/vite.config.ts
    
    Then you need to configure your framework to use the internal IP. For [**Vite**](https://next--tauri.netlify.app/next/mobile/development/configuration#vite), you need to change your configuration to be defined using the defineConfig helper with an async closure. Then, resolve the internal IP address and set it to the [server object](https://vitejs.dev/config/server-options.html).
    
    - Было —— Стало
        
        
        Было
        
        ```jsx
        import * as path from 'path';
        import { defineConfig } from "vite";
        import { svelte } from "@sveltejs/vite-plugin-svelte";
        import sveltePreprocess from "svelte-preprocess";
        
        // https://vitejs.dev/config/
        export default defineConfig({
          resolve: {
            alias: {
              '@': path.resolve(__dirname, './src'),
            },
          },
        
          plugins: [
            svelte({
              preprocess: [
                sveltePreprocess({
                  typescript: true,
                }),
              ],
            }),
          ],
        
          // Vite options tailored for Tauri development and only applied in `tauri dev` or `tauri build`
          // prevent vite from obscuring rust errors
          clearScreen: false,
          // tauri expects a fixed port, fail if that port is not available
          server: {
            port: 1420,
            strictPort: true,
          },
          // to make use of `TAURI_DEBUG` and other env variables
          // https://tauri.studio/v1/api/config#buildconfig.beforedevcommand
          envPrefix: ["VITE_", "TAURI_"],
          build: {
            // Tauri supports es2021
            target: process.env.TAURI_PLATFORM == "windows" ? "chrome105" : "safari13",
            // don't minify for debug builds
            minify: !process.env.TAURI_DEBUG ? "esbuild" : false,
            // produce sourcemaps for debug builds
            sourcemap: !!process.env.TAURI_DEBUG,
          },
        });
        ```
        
        Стало
        
        ```jsx
        import * as path from 'path';
        import { defineConfig } from "vite";
        import { svelte } from "@sveltejs/vite-plugin-svelte";
        import sveltePreprocess from "svelte-preprocess";
        import { internalIpV4 } from 'internal-ip'
        
        // https://vitejs.dev/config/
        export default defineConfig(async () => {
          const host = await internalIpV4()
        
          return {
            resolve: {
              alias: {
                '@': path.resolve(__dirname, './src'),
              },
            },
        
            plugins: [
              svelte({
                preprocess: [
                  sveltePreprocess({
                    typescript: true,
                  }),
                ],
              }),
            ],
        
            // Vite options tailored for Tauri development and only applied in `tauri dev` or `tauri build`
            // prevent vite from obscuring rust errors
            clearScreen: false,
            // tauri expects a fixed port, fail if that port is not available
            server: {
              host: '0.0.0.0', // listen on all addresses
              port: 1420,
              strictPort: true,
              hmr: {
                protocol: 'ws',
                host,
                port: 1420,
              },
            },
            // to make use of `TAURI_DEBUG` and other env variables
            // https://tauri.studio/v1/api/config#buildconfig.beforedevcommand
            envPrefix: ["VITE_", "TAURI_"],
            build: {
              // Tauri supports es2021
              target: process.env.TAURI_PLATFORM == "windows" ? "chrome105" : "safari13",
              // don't minify for debug builds
              minify: !process.env.TAURI_DEBUG ? "esbuild" : false,
              // produce sourcemaps for debug builds
              sourcemap: !!process.env.TAURI_DEBUG,
            }
          }
        });
        ```
        

[Integrate into Existing Project | Tauri Apps](https://next--tauri.netlify.app/next/mobile/development/integrate)

[**Change your crate type**](https://next--tauri.netlify.app/next/mobile/development/integrate/#change-your-crate-type)

- [project]/src-tauri/Cargo.toml
    
    ```xml
    [lib]
    crate-type = ["staticlib", "cdylib", "rlib"]
    ```
    

**[Create the library source code](https://next--tauri.netlify.app/next/mobile/development/integrate/#create-the-library-source-code)**

- [project]/src-tauri/src/lib.rs
    
    ```jsx
    use tauri::App;
    
    #[cfg(mobile)]
    mod mobile;
    #[cfg(mobile)]
    pub use mobile::*;
    
    pub type SetupHook = Box<dyn FnOnce(&mut App) -> Result<(), Box<dyn std::error::Error>> + Send>;
    
    #[derive(Default)]
    pub struct AppBuilder {
      setup: Option<SetupHook>,
    }
    
    impl AppBuilder {
      pub fn new() -> Self {
        Self::default()
      }
    
      #[must_use]
      pub fn setup<F>(mut self, setup: F) -> Self
      where
        F: FnOnce(&mut App) -> Result<(), Box<dyn std::error::Error>> + Send + 'static,
      {
        self.setup.replace(Box::new(setup));
        self
      }
    
      pub fn run(self) {
        let setup = self.setup;
        tauri::Builder::default()
          .setup(move |app| {
            if let Some(setup) = setup {
              (setup)(app)?;
            }
            Ok(())
          })
          .run(tauri::generate_context!())
          .expect("error while running tauri application");
      }
    }
    ```
    
    [lib.rs](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/befcbd73-b95e-48e0-be1d-fade8fa09df2/lib.rs)
    
- [project]/src-tauri/src/mobile.rs
    
    ```jsx
    #[tauri::mobile_entry_point]
    fn main() {
      super::AppBuilder::new().run();
    }
    ```
    
    [mobile.rs](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ee7aee34-db84-4ece-9417-38aaa8f7f747/mobile.rs)
    
- [project]/src-tauri/src/main.rs
    
    Было
    
    ```jsx
    #![cfg_attr(
        all(not(debug_assertions), target_os = "windows"),
        windows_subsystem = "windows"
    )]
    
    // Learn more about Tauri commands at https://tauri.app/v1/guides/features/command
    #[tauri::command]
    fn greet(name: &str) -> String {
        format!("Hello, {}! You've been greeted from Rust!", name)
    }
    
    fn main() {
        tauri::Builder::default()
            .invoke_handler(tauri::generate_handler![greet])
            .run(tauri::generate_context!())
            .expect("error while running tauri application");
    }
    ```
    
    Стало
    
    ```jsx
    #![cfg_attr(
        all(not(debug_assertions), target_os = "windows"),
        windows_subsystem = "windows"
    )]
    
    pub fn main() {
        client::AppBuilder::new().run();
    }
    
    ```
    
    [main.rs](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6668ed10-e128-47de-a58f-eb7e21700805/main.rs)
    
- [project]/dist
    
    **Error:** The `distDir` configuration is set to `"../dist"` but this path doesn't exist
    
    ```bash
    mkdir ~/git/club-client/dist
    ```
    
- [project]/src-tauri/tauri.conf.json
    
    "productName": "Гермес",
    
    category": "Business”,
    
    "identifier": "ru.clubgermes.social.client",
    
    "iOS": {
      "developmentTeam": "XRF3344MB3"
    },
    
    - **???** "productName": Play Market - "Гермес” а у AppStore — “Гермес клуб”, наверное, закину в info.plist **???**
    - "identifier": "ru.clubgermes.social.client"
        
        **Error:** You must change the bundle identifier
        in `tauri.conf.json > tauri > bundle > identifier`. The default value `com.tauri.dev` is not allowed as it must be unique across applications.
        
        в файле tauri.conf.json поменять "identifier": "ru.clubgermes.social.client",
        
        Save your changes, delete the `gen/android` folder and run `tauri android init` to recreate the Android project.
        
    - [project]/src-tauri/tauri.conf.json "iOS": { "developmentTeam": "XRF3344MB3" },
        
        Error No code signing certificates found. You must add one and set the certificate development team ID on the `tauri > bundle > iOS > developmentTeam` config value or the `TAURI_APPLE_DEVELOPMENT_TEAM` environment variable. To list the available certificates, run `tauri info`.
        
        ```jsx
        //tauri.conf.json -->> tauri > bundle > iOS > developmentTeam
        "iOS": {
                "developmentTeam": "XRF3344MB3"
              },
        //Generate the required iOS source code:
        npm run tauri ios init
        ```
        
    - Error `tauri.conf.json` error on `tauri`: Additional properties are not allowed ('allowlist', 'updater' were unexpected)
        
        Delete
        
        - `tauri.conf.json`
            
            ```json
            {
              "build": {
                "beforeDevCommand": "npm run dev",
                "beforeBuildCommand": "npm run build",
                "devPath": "http://localhost:1420",
                "distDir": "../dist",
                "withGlobalTauri": false
              },
              "package": {
                "productName": "Гермес",
                "version": "0.0.0"
              },
              "tauri": {
                "bundle": {
                  "active": true,
                  "category": "DeveloperTool",
                  "copyright": "",
                  "deb": {
                    "depends": []
                  },
                  "externalBin": [],
                  "icon": [
                    "icons/32x32.png",
                    "icons/128x128.png",
                    "icons/128x128@2x.png",
                    "icons/icon.icns",
                    "icons/icon.ico"
                  ],
                  "identifier": "ru.clubgermes.social.client",
                  "longDescription": "",
                  "macOS": {
                    "entitlements": null,
                    "exceptionDomain": "",
                    "frameworks": [],
                    "providerShortName": null,
                    "signingIdentity": null
                  },
                  "iOS": {
                    "developmentTeam": "XRF3344MB3" 
                  },
                  "resources": [],
                  "shortDescription": "",
                  "targets": "all",
                  "windows": {
                    "certificateThumbprint": null,
                    "digestAlgorithm": "sha256",
                    "timestampUrl": ""
                  }
                },
                "security": {
                  "csp": null
                },
                "windows": [
                  {
                    "fullscreen": false,
                    "height": 600,
                    "resizable": true,
                    "title": "client",
                    "width": 800
                  }
                ]
              }
            }
            ```
            
- [project]/app-icon.png —— png, 1024x1024px **with transparency**
    
    [Icons | Tauri Apps](https://next--tauri.netlify.app/next/guides/features/icons)
    

```bash
cargo tauri icon
```

непонятки:

- ??? [project]/src-tauri/Info.plist ??? почему-то не используется в сборке, приходится копировать руками
    
    <aside>
    ⚠️ **[INFO](https://tauri.app/v1/guides/building/macos/)** These options generate the application bundle [Info.plist file](https://developer.apple.com/library/archive/documentation/General/Reference/InfoPlistKeyReference/Introduction/Introduction.html). You can extend the generated file with your own `Info.plist`file stored in the Tauri folder (`src-tauri` by default). The CLI merges both `.plist` files in production, and the core layer embeds it in the binary during development.
    
    </aside>
    
    - **Info.plist**
        
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
        <plist version="1.0">
        <dict>
        	<key>CFBundleDevelopmentRegion</key>
        	<string>$(DEVELOPMENT_LANGUAGE)</string>
        	<key>CFBundleExecutable</key>
        	<string>client</string>
        	<key>CFBundleIdentifier</key>
        	<string>$(PRODUCT_BUNDLE_IDENTIFIER)</string>
        	<key>CFBundleInfoDictionaryVersion</key>
        	<string>6.0</string>
        	<key>CFBundleName</key>
        	<string>Гермес клуб</string>
        	<key>CFBundlePackageType</key>
        	<string>APPL</string>
        	<key>CFBundleShortVersionString</key>
        	<string>0.0.3</string>
        	<key>CFBundleVersion</key>
        	<string>0.0.0</string>
        	<key>LSRequiresIPhoneOS</key>
        	<true/>
        	<key>NSCameraUsageDescription</key>
        	<string>Это позволит Вам сфотографировать себя для профиля или документ для собеседника в чате.</string>
        	<key>NSMicrophoneUsageDescription</key>
        	<string>Это позволит Вам записать голосовое сообщение для собеседника в чате.</string>
        	<key>NSPhotoLibraryUsageDescription</key>
        	<string>Это позволит Вам добавить изображение из библиотеки Вашего устройства в профиль или в чат.</string>
        	<key>UILaunchStoryboardName</key>
        	<string>LaunchScreen</string>
        	<key>UIRequiredDeviceCapabilities</key>
        	<array>
        		<string>arm64</string>
        		<string>metal</string>
        	</array>
        	<key>UISupportedInterfaceOrientations</key>
        	<array>
        		<string>UIInterfaceOrientationPortrait</string>
        		<string>UIInterfaceOrientationLandscapeLeft</string>
        		<string>UIInterfaceOrientationLandscapeRight</string>
        	</array>
        	<key>UISupportedInterfaceOrientations~ipad</key>
        	<array>
        		<string>UIInterfaceOrientationPortrait</string>
        		<string>UIInterfaceOrientationPortraitUpsideDown</string>
        		<string>UIInterfaceOrientationLandscapeLeft</string>
        		<string>UIInterfaceOrientationLandscapeRight</string>
        	</array>
        </dict>
        </plist>
        ```
        
- ??? Локализация ??? просто поменял язык в сойствах проекта Xcode
    - [ ]  Перевести на русский меню загрузки нового фото на аватар
    
- ??? [project]/src-tauri/Cargo.toml ошибка net::ERR_CONNECTION_REFUSED ??????????? вроде Ок!
    
    ### I/RustStdoutStderr: Failed to request http://192.168.0.102:1420/:
    
    error sending request for url (http://192.168.0.102:1420/): error trying to connect: tcp connect error: Connection refused (os error 111)
    
    https://github.com/tauri-apps/tauri/issues/6454
    

## **Android**

[Android - Sigining APKs and AABs | Tauri Apps](https://next--tauri.netlify.app/next/guides/distribution/sign-android)

```bash
rm -r src-tauri/gen/android
cargo tauri android init
cargo tauri android dev
```

- [project]/src-tauri/gen/android/[your-app]/local.properties
    
    ```jsx
    ## This file must *NOT* be checked into Version Control Systems,
    # as it contains information specific to your local configuration.
    #
    # Location of the SDK. This is only used by Gradle.
    # For customization when using a Version Control System, please read the
    # header note.
    #Fri Apr 21 13:23:30 MSK 2023
    sdk.dir=/Users/valeriigrizodubov/Library/Android/sdk
    storePassword=02@Enteraboksal
    keyPassword=02@Enteraboksal
    keyAlias=digitender-key0
    storeFile=/Users/valeriigrizodubov/Documents/keystores/upload-keystore
    ```
    
    [local.properties](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/66a4a8e1-840c-4741-ab25-f5b57ff887b1/local.properties)
    
- [project]/src-tauri/gen/android/[your-app]/app/build.gradle.kts
    
    ```jsx
    import java.util.Properties
    import java.io.FileInputStream
    
    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = Properties()
    localProperties.load(FileInputStream(localPropertiesFile))
    
    android {
       ...
    }
    
    signingConfigs {
       create("release") {
           keyAlias = localProperties["keyAlias"] as String
           keyPassword = localProperties["keyPassword"] as String
           storeFile = file(localProperties["storeFile"] as String)
           storePassword = localProperties["storePassword"] as String
       }
    }
    
    buildTypes {
       ...
    }
    
    buildTypes {
      ...
      getByName("release") {
        ...
        signingConfig = signingConfigs.getByName("release")
      }
    }
    ```
    
    [build.gradle.kts](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/18517404-855a-4ee0-9a5e-7dac79a85219/build.gradle.kts)
    
- [project]/src-tauri/gen/android/[your-app]/app/build.gradle.kts isMinifyEnabled = false
    
    ### **Android application built in release mode crashes at startup**
    
    https://github.com/tauri-apps/tauri/issues/6560
    
    ```jsx
    getByName("release") {
        isMinifyEnabled = false
    }
    ```
    
- [project]/src-tauri/gen/android/[your-app]/app/build.gradle.kts versionCode = 9
    
    [Загрузка нового apk приложения - ошибка номера версии](https://ru.stackoverflow.com/questions/510866/Загрузка-нового-apk-приложения-ошибка-номера-версии)
    
- [project]/src-tauri/gen/android/[your-app]/app/src/main/res/values/strings.xml —— название приложения = “Гермес”.
    
    ```jsx
    <resources>
        <string name="app_name">Гермес</string>
        <string name="main_activity_title">Гермес</string>
    </resources>
    ```
    

```bash
cargo tauri android build
```

- Logs
    
    [Starting: Intent { cmp=ru.clubgermes.social.client/.MainActivity }](https://www.notion.so/Starting-Intent-cmp-ru-clubgermes-social-client-MainActivity-28f77027b24f464f8cf322e671138b23?pvs=21)
    
    [Starting: Intent { cmp=ru.clubgermes.social.client/.MainActivity } (MB 25апр 13:41)](https://www.notion.so/Starting-Intent-cmp-ru-clubgermes-social-client-MainActivity-MB-25-13-41-c02a36054622461188e0c848c5e7b7ca?pvs=21)
    
    [Android studio logcast](https://www.notion.so/Android-studio-logcast-3552a530a06748c6ab50fe280ef0bdf5?pvs=21)
    
    [clubgermes git:(master) ✗ cargo tauri android build (iMac 24апр 17:00)](https://www.notion.so/clubgermes-git-master-cargo-tauri-android-build-iMac-24-17-00-04843506262e4524991609a2bf4df591?pvs=21)
    
    [clubgermes git:(master) ✗ cargo tauri android build (MB 25апр 13:31)](https://www.notion.so/clubgermes-git-master-cargo-tauri-android-build-MB-25-13-31-3205eb8c65884318a31b5fdaaa84d66a?pvs=21)
    

## iOS

```bash
rm -r src-tauri/gen/apple
cargo tauri ios init
cargo tauri ios dev
```

[Code Signing macOS Applications | Tauri Apps](https://next--tauri.netlify.app/next/guides/distribution/sign-macos)

- [project]/app-icon.png —— png, 1024x1024px **without transparency**
    
    [Icons | Tauri Apps](https://next--tauri.netlify.app/next/guides/features/icons)
    
- [ ]  Remove alfa channel /git/clubgermes/src-tauri/gen/apple/Assets.xcassets/AppIcon.appiconset
- [ ]  Edit assets in Xcode! убрать не используемое изображение
- [ ]  Change Build Settings —— Packaging —— Product Name — “Гермес клуб” или в [project]/src-tauri/gen/apple/client_iOS/Info.plist
- Xcode —— Supported Destinations — delete “iPad”
    
    Asset validation failed Because your app supports Multitasking on iPad
    
    Invalid bundle. Because your app supports Multitasking on iPad, you need to include the LaunchScreen launch storyboard file in your ru.clubgermes.social.client bundle. Use UILaunchScreen instead if the app’s MinimumOSVersion is 14 or higher and you prefer to configure the launch screen without storyboards. For details, see: https://developer.apple.com/documentation/bundleresources/information_property_list/uilaunchstoryboardname (ID: 75a156ec-07e3-4c8c-8c06-d6f0c58cbc4b)
    
- Xcode —— Build Phases —— Copy Bundle Resources — delete “libclient.a”
    
    Asset validation failed & library not found for -lclient
    
    Invalid bundle structure. The “client.app/libclient.a” binary file is not permitted. Your app cannot contain standalone executables or libraries, other than a valid CFBundleExecutable of supported bundles. For details, visit: https://developer.apple.com/documentation/bundleresources/placing_content_in_a_bundle (ID: c7f68009-5acb-4cc4-ad8d-413aa672a8eb)
    
    /Users/valeriigrizodubov/git/club-client/src-tauri/gen/apple/Externals/debug/libclient.a
    
    ### ld: library not found for -lclient
    
    clang: error: linker command failed with exit code 1 (use -v to see invocation)
    * BUILD FAILED **
    The following build commands failed:
    Ld /Users/valeriigrizodubov/Library/Developer/Xcode/DerivedData/client-deajaidfbsobikgqprmdxuhsjvof/Build/Products/debug-iphoneos/client.app/client normal (in target 'client_iOS' from project 'client')
    (1 failure)
    Error command ["xcodebuild"] exited with code 65
    
    ```bash
    
    ```
    
    [Unable to upload iOS update after adding Flurry Analytics? Error: Your app can’t contain standalone executables or libraries](https://stackoverflow.com/questions/30419119/unable-to-upload-ios-update-after-adding-flurry-analytics-error-your-app-can-t)
    
- [ ]  Invalid Pre-Release Train. The train version '0.0.0' is closed for new build submissions. This bundle is invalid. The value for key CFBundleShortVersionString [0.0.0] in the Info.plist file must contain a higher version than that of the previously approved version [0.0.0]. Please find more information about CFBundleShortVersionString at https://developer.apple.com/documentation/bundleresources/information_property_list/cfbundleshortversionstring

```bash
cargo tauri ios build
```

непонятки:

- вроде в новой Таури исправили [project]/src-tauri/gen/apple/Externals/debug **mkdir**
    
    ld: warning: directory not found for option
    
    '-L/Users/valeriigrizodubov/git/club-client/src-tauri/gen/apple/Externals/debug’
    
    ```bash
    mkdir ~/git/club-client/src-tauri/gen/apple/Externals/debug
    ```
    

## **[Updating Dependencies](https://tauri.app/v1/guides/development/updating-dependencies/)**

```bash
sudo lsof -i:1420
sudo kill -9 92375
```

[Tauri (draft)](https://www.notion.so/Tauri-draft-b5dd6154664e4f34828b4e61af054a21?pvs=21)

https://github.com/tauri-apps/tauri/issues/6208
