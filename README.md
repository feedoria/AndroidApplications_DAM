# Android Applications – DAM

Android applications built in **Java** for the *Mobile Devices and Applications* (DAM – Dispozitive și aplicații mobile) course at the Bucharest University of Economic Studies (ASE), Faculty of Cybernetics, Statistics and Economic Informatics.

The repository contains the lab projects (labs 1–5) and a set of practice exercises written while preparing for the practical test.

## Tech stack

- Java (one Kotlin intro project)
- Android Studio
- Gradle (Kotlin DSL)
- Android SDK – Activities, Intents, ListView, Adapters, Menus, Dialogs

## Repository structure

### Labs

| Project | Description |
|---|---|
| `Seminar1` | First Android app: layouts, basic views and image resources |
| `Sem1_ig` | Intro project written in Kotlin |
| `Sem2_Remake_ExBiblioteca` | Book library app with a form activity and a second screen |
| `SemDAM_1085` | Main lab project: `Student` model, add form, list display, BNR exchange-rate screen, options menu |
| `Seminar4/SemDAM_1085` | Custom adapters (`CustomAdapter`, `CustomAdapter2`) and exchange-rate list |
| `Seminar5_git/SemDAM_1085` | Network access and data parsing (JSON, XML, CSV) |
| `Seminar5_git_lucruJSON*` | JSON parsing practice (3 iterations) |
| `Seminar5_git_lucruXML` | XML parsing practice |

### Practice exercises

| Project | Main topics |
|---|---|
| `Ex1_Masini` | Form, `startActivityForResult`, custom adapter |
| `Ex2_Concerte` | Form with validation, list in main activity |
| `Ex3_Rezervari` | Form, date parsing, list display |
| `Ex4_Antrenamente` | Custom adapter with row template |
| `Ex5_Zboruri` | Form → main activity list flow |
| `Ex_Filme` | Custom adapter, list in main activity |
| `Ex_Biblioteci` | Form in main activity, list sent to a second activity |
| `Ex_Cabinet_Veterinar` | Options menu, CheckBox, delete with AlertDialog |
| `Ex_ClubSportiv` | Spinner, RadioGroup, custom adapter with conditional colouring, AlertDialog |
| `Ex_AgentieTurism` | Options menu, CheckBox, custom adapter, item counter, delete |
| `Ex_CabinetStomatologic` | List in main activity, RadioGroup, coloured `ArrayAdapter`, edit with pre-filled form, delete, statistics menu |
| `Ex_Florarie` | Switch, buttons + menu, custom adapter with colouring, delete with AlertDialog |
| `Ex_Editare` | Edit flow practice |

## Concepts covered

- Activities, activity lifecycle, titles and action bar
- Explicit Intents: `startActivity`, `startActivityForResult` / `setResult` / `onActivityResult`
- Passing `Serializable` objects and lists between activities (`putExtra` / `getSerializableExtra`)
- Forms: EditText, Spinner, CheckBox, Switch, RadioGroup, input validation with `setError`
- Dates with `SimpleDateFormat` (`parse` / `format`)
- ListView with `ArrayAdapter` and custom adapters (`getView`, `LayoutInflater`, row templates)
- Click and long-click listeners, `AlertDialog`, `Toast`
- Editing and deleting list items, `notifyDataSetChanged`
- Options menus (`onCreateOptionsMenu` / `onOptionsItemSelected`)

## How to run

1. Clone the repository:
   ```
   git clone https://github.com/feedoria/AndroidApplications_DAM.git
   ```
2. In Android Studio, choose **File → Open** and select one project folder (for example `Ex_Florarie`), not the repository root.
3. Let Gradle sync, then run the app on an emulator or a device.
