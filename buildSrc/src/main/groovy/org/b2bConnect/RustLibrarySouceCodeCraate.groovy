package org.b2bConnect

class RustLibrarySouceCodeCraate {
    static void main(String[] args) {

        def libRsFileText = '''
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
'''

        def mainRssFileText = '''
#![cfg_attr(
    all(not(debug_assertions), target_os = "windows"),
    windows_subsystem = "windows"
)]

pub fn main() {
    client::AppBuilder::new().run();
}
'''

        def mobileRsFileText = '''
#[tauri::mobile_entry_point]
fn main() {
  super::AppBuilder::new().run();
}
'''
        def projectDirPath = new ProjectDirUriGet().ProjectDirUri()
        def newFile = new File("$projectDirPath/src-tauri/src/lib.rs")
        PasteTextByIndex.main("src-tauri/src/lib.rs", "$libRsFileText", 0, false)
        PasteTextByIndex.main("src-tauri/src/main.rs", "$mainRssFileText", 0, false)
        PasteTextByIndex.main("src-tauri/src/mobile.rs", "$mobileRsFileText", 0, false)
    }
}