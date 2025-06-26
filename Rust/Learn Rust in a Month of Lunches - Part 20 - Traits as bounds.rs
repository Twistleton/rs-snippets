trait French {}
trait LawyerSkill {}
trait MedicalSkill {}

struct FrenchCitizen;
struct ExchangeStudentInFrance;
struct AmericanLawyer;
struct AmericanDoctor;
struct FrenchLawyer;
struct FrenchDoctor;
struct MrKnowsEverything;

impl French for FrenchCitizen {}
impl French for ExchangeStudentInFrance {}
impl French for FrenchLawyer {}
impl French for FrenchDoctor {}
impl French for MrKnowsEverything {}

impl LawyerSkill for AmericanLawyer {}
impl LawyerSkill for FrenchLawyer {}
impl LawyerSkill for MrKnowsEverything {}

impl MedicalSkill for AmericanDoctor {}
impl MedicalSkill for FrenchDoctor {}
impl MedicalSkill for MrKnowsEverything {}

fn speak_french<T: French>(speaker: T) {}

fn enter_court<T: LawyerSkill>(lawyer: T) {}

fn cure_patient<T: MedicalSkill>(doctor: T) {}

fn enter_french_court<T: LawyerSkill + French>(lwayer: T) {}

fn cure_french_patient<T: MedicalSkill + French>(doctor: T) {}

fn present_medical_case_in_french_court<T: MedicalSkill + LawyerSkill + French>(lawyer: T) {}

fn main() {
    speak_french(FrenchCitizen);

    enter_court(AmericanLawyer);

    cure_patient(AmericanDoctor);

    enter_french_court(FrenchLawyer);

    cure_french_patient(FrenchDoctor);

    present_medical_case_in_french_court(MrKnowsEverything);
}