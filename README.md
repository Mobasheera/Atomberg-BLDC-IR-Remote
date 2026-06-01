## Overview

Atomberg BLDC IR Remote is an open-source Android application that allows compatible smartphones with an infrared (IR) blaster to control Atomberg BLDC ceiling fans.

The application was developed by reverse-engineering and verifying Atomberg's infrared command set, providing a lightweight and reliable alternative to the physical remote. It directly transmits NEC-format IR commands using Android's ConsumerIrManager API and does not require internet access, Bluetooth, Wi-Fi, or any external hardware.

The current version supports essential fan controls including power, speed selection, boost mode, timer mode, and sleep mode.

---

## Features

* Power ON/OFF control
* Speed selection (Levels 1–5)
* Boost mode support
* Timer mode support
* Sleep mode support
* Native IR transmission using Android ConsumerIrManager
* Verified NEC protocol implementation (38 kHz)
* Haptic feedback on button presses
* Lightweight and responsive interface
* No advertisements
* No internet connection required
* Open-source project

## Screenshots

### Main Interface

| App UI                     |
| -------------------------- |
| *(Insert screenshot here)* |

The application replicates the layout of the original Atomberg remote while providing direct infrared transmission from supported Android devices.

---

## Supported Functions

The following functions have been verified and tested with Atomberg BLDC fans:

| Function    | Status            |
| ----------- | ----------------- |
| Power       | ✅ Supported       |
| Speed 1     | ✅ Supported       |
| Speed 2     | ✅ Supported       |
| Speed 3     | ✅ Supported       |
| Speed 4     | ✅ Supported       |
| Speed 5     | ✅ Supported       |
| Boost Mode  | ✅ Supported       |
| Timer Mode  | ✅ Supported       |
| Sleep Mode  | ✅ Supported       |
| LED Control | ❌ Not implemented |

### Verified NEC Commands

| Function | NEC Code   |
| -------- | ---------- |
| Power    | `6E91F300` |
| Speed 1  | `748BF300` |
| Speed 2  | `6F90F300` |
| Speed 3  | `758AF300` |
| Speed 4  | `6C93F300` |
| Speed 5  | `7788F300` |
| Boost    | `708FF300` |
| Timer    | `6996F300` |
| Sleep    | `718EF300` |

### IR Protocol Details

* Protocol: NEC
* Carrier Frequency: 38 kHz
* Bit Order: LSB First
* Header Mark: 9000 µs
* Header Space: 4500 µs
* Bit Mark: 560 µs
* Logic 0 Space: 560 µs
* Logic 1 Space: 1690 µs
* Trailer Mark: 560 µs

## Requirements

### Supported Devices

This application requires an Android device equipped with a built-in infrared (IR) blaster.

### Tested Devices

| Device            | Status   |
| ----------------- | -------- |
| Redmi Note 10 Pro | ✅ Tested |
| Redmi Note 8 Pro  | ✅ Tested |

### Fan Compatibility

The following Atomberg fan series are known to use the supported IR command set:

#### Aris Series

* Aris
* Aris Smart
* Aris Starlight
* Aris Starlight Smart
* Aris Contour
* Aris Gladius
* Aris Gladius Smart

#### Studio Series

* Studio+
* Studio Smart+
* Studio Nexus

#### Erica Series

* Erica
* Erica Smart
* Erica Meta
* Erica Nuvo

#### Renesa Series

* Renesa+
* Renesa Smart+
* Renesa Prime Remote
* Renesa Elite
* Renesa Halo

#### Other Models

* Ikano
* Efficio
* Efficio 2
* Efficio Prime

### Verified Hardware

The application has been personally tested and verified with:

* Atomberg Erica Meta
* Redmi Note 10 Pro
* Redmi Note 8 Pro

Compatibility with additional supported models is based on the shared IR command set and community verification.

---

## Installation

### Option 1: Download APK

1. Download the latest APK from the GitHub Releases page.
2. Transfer the APK to your Android device if required.
3. Install the APK.
4. Allow installation from unknown sources if prompted.

### Option 2: Build From Source

1. Clone the repository:

```bash
git clone https://github.com/Mobasheera/Atomberg-BLDC-IR-Remote.git
```

2. Open the project in Android Studio.
3. Allow Gradle to sync dependencies.
4. Connect an Android device with an IR blaster.
5. Build and run the application.

---

## Usage

1. Open the application.
2. Point the phone's IR emitter toward the fan receiver.
3. Press the desired button on the virtual remote.
4. The command is transmitted immediately.

For supported commands, refer to the **Supported Functions** section above.
