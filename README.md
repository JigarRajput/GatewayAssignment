# GatewayAssignment

An Android app built with Kotlin and Jetpack Compose demonstrating deep link handling, Retrofit API calls, and Chrome Custom Tabs integration.

---

## Getting Started

Follow these steps to get the project running on your machine:

### Step 1: Clone the repository

```bash
git clone https://github.com/JigarRajput/GatewayAssignment.git
cd GatewayAssignment
```
### Step 2: Open the project

1. Open **Android Studio**.  
2. Click **Open an Existing Project**.  
3. Navigate to the cloned `GatewayAssignment` folder.  
4. Click **OK** to open the project.

### Step 3: Build & Sync

- Wait for Gradle to sync and download all dependencies automatically.

### Step 4: Run the app

1. Connect your Android device or start an emulator.  
2. Click the **Run** button in Android Studio and select your device to launch the app.

## Project Details

- **Minimum SDK:** 24  
- **Target SDK:** 35  
- **Compose UI:** Enabled  
- **Retrofit:** For network calls  
- **Gson:** For JSON deserialization  
- **Chrome Custom Tabs:** For in-app browser support  
- **Coroutines:** For asynchronous programming  

## Deep Link Support

The app supports deep links with scheme `sc-assignment` and host `home` to handle URLs like:  
`sc-assignment://home/redirect?status=...&code=...&data=...`

## Permissions

- The app requires **Internet access** for network calls.
