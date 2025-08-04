# 📝 Personal Task Manager App

A simple and intuitive Android application for managing daily personal tasks. The app features user authentication using Firebase, allowing users to register and log in securely to manage their own to-do lists.

---

## 🚀 Features

- 🔐 **Firebase Authentication**
  - User Registration (Email & Password)
  - Secure Login
  - Error handling for invalid login or registration

- ✅ **Task Management** *(in progress)*
  - Add, edit, delete tasks
  - Mark tasks as complete or pending
  - View filtered task lists (Pending / Completed)

---

## 📱 Screenshots

> *(Add screenshots of Login/Register UI here if available)*

---

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Android SDK
- **Architecture:** MVVM (Model-View-ViewModel)
- **Backend:** Firebase Authentication & Firebase Realtime Database
- **UI:** Material Design Components

---

## 🔐 Firebase Setup

To run this project with Firebase support:

1. Go to [Firebase Console](https://console.firebase.google.com/) and create a new project.
2. Enable **Email/Password** authentication.
3. Download the `google-services.json` file.
4. Place it inside the `/app` folder of your Android Studio project.
5. Sync Gradle.

---

## 📂 Project Structure

├── app/ \
│ ├── java/com.rzjaffery.personaltaskmanagerapp/ \
│ │ ├── ui/ # Login, Register, Task activities \
│ │ ├── model/ # User model class \
│ │ ├── repository/ # Firebase operations \
│ │ └── viewmodel/ # AuthViewModel (MVVM) \
│ ├── res/layout/ # activity_login.xml, activity_register.xml, etc. \
│ └── google-services.json # Firebase config \


---

## 🔧 How to Run

1. Clone the repository:
   ```bash  git clone https://github.com/rzjaffery/Personal-Task-Manager-Authentication.git
2. Open in Android Studio.

3. Place google-services.json in the /app directory.

4. Sync Gradle & Run on emulator or physical device.

## Dependencies Used

// Firebase
implementation("com.google.firebase:firebase-auth:22.3.1")
implementation("com.google.firebase:firebase-database:20.3.1")

// AndroidX
implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
implementation("androidx.lifecycle:lifecycle-livedata:2.6.2")
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("com.google.android.material:material:1.12.0")


