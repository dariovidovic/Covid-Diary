package com.example.covid_journal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference


class PollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val currentUserID = auth.currentUser?.uid.toString()
        var dayCounter = 0


        val temperature = findViewById<EditText>(R.id.enterTemperature)

        val difficultyBreathing = findViewById<RadioGroup>(R.id.difficultyBreathing)
        val mobility = findViewById<RadioGroup>(R.id.mobility)
        val chestPain = findViewById<RadioGroup>(R.id.chestPain)
        val lossOfSense = findViewById<RadioGroup>(R.id.lossOfSense)
        val cough = findViewById<RadioGroup>(R.id.cough)
        val headache = findViewById<RadioGroup>(R.id.headache)
        val soreThroat = findViewById<RadioGroup>(R.id.soreThroat)
        val tiredness = findViewById<RadioGroup>(R.id.tiredness)
        val bodyAches = findViewById<RadioGroup>(R.id.bodyAches)
        val runnyNose = findViewById<RadioGroup>(R.id.runnyNose)
        val nausea = findViewById<RadioGroup>(R.id.nausea)

        database.child(currentUserID).get().addOnSuccessListener {
            dayCounter = it.child("dayCounter").value.toString().toInt()
        }.addOnFailureListener {
            dayCounter = 0
        }


        val goBackFromPoll = findViewById<Button>(R.id.goBackFromPollButton)
        val savePoll = findViewById<Button>(R.id.savePollButton)

        var allAdvices = ""
        var emergencyAdvice = "Pozovite hitnu medicinsku pomo??! Prije odlaska doktoru/zvanja medicinske pomo??i obavijestite da mo??da imate COVID-19.\n\n"

        var headacheAdvices = "Lagana ??etnja poma??e pri glavobolji jer smanjiva bol i napetost u mi??i??ima, ubla??uje stres te promi??e san. Vodite ra??una da ne pretjerate da ne bi postigli suprotan efekt ??? jo?? goru glavobolju.\n" +
                "Izbjegavajte prekomjernu konzumaciju kafeina.\n" +
                "Topli tu?? djeluje na ubla??avanje napetosti mi??i??a. To mo??e Va??u glavobolju u??initi lak??om i kra??om.\n" +
                "Tablete za glavobolju, poput Lupoceta, poma??u u smanjivanju bolova izazvanih glavoboljom.\n" +
                "Odr??avanje hidratacije i dovoljno sna poma??e u borbi protiv glavobolje.\n\n"

        var coughAdvices = "Ostanite dobro hidrirani. Unos puno teku??ine razrje??uje sluz, ??to doprinosi iska??ljavanju.\n" +
                "Ukoliko vam ka??alj ote??ava spavanje, spavajte s podignutim uzglavljem.\n" +
                "Pijte tople napitke! Osim ??to zagrijavaju di??ne puteve, dr??e vas hidratiziranim te razgra??uju sluz koju imate u grlui gornjim di??nim putevima.\n" +
                "U prehrani se preporu??uju juhe, tople ka??e i med.\n" +
                "Ukoliko imate suhi ka??alj, probajte lijekove ili prirodne sirupe kako bi vam smanjili ili blokirali refleks ka??lja.\n\n"

        var smokerAdvices = "Pu??enje uzrokuje probleme s plu??ima, ??to mo??e biti veoma opasno jer COVID-19 napada plu??a." +
            "Prestanak pu??enja u borbi s COVID-19 je najbolje rje??enje. \n\n"

        var lossOfSenseAdvice = "Gubitak osjeta njuha/okusa je ??esti simptom COVID-19. Samoizolirajte se i testirajte se na COVID-19.\n\n"

        var nauseaAdvices = "Jedite blagu hranu jer ona obi??no ne iritira ??eludac i poma??e u ubla??avanju mu??nine.\n" +
                "Pijte puno vode kako biste ostali hidrirani, osobito ako ste povra??ali.\n" +
                "Izbjegavajte masnu, za??injenu ili slatku hranu jer ona mo??e pogor??ati mu??ninu.\n" +
                "Izbjegavajte pi??a s kofeinom jer ona mogu uznemiriti ??eludac.\n" +
                "Jedite male obroke jer oni omogu??avaju va??em ??elucu da probavi hranu postupno.\n\n"

        var bodyAchesAdvices = "Unos puno teku??ine i odmor obi??no su dovoljni za potpun oporavak od bolova u tijelu.\n"+
                "Uzmite lijek za smanjenje boli u tijelu i mi??i??ima.\n\n"

        var runnyNoseAdvices = "Koristite sprej za nos jer oni pridonose kontrakciji krvnih ??ila u sluznici nosa i olak??avaju disanje. Nemojte koristiti sprej za nos preko 7 dana bez savjetovanja s lije??nikom.\n" +
                "Topli tu?? ili udisanje pare poma??e pri lak??em disanju jer inhalacija vodene pare umiruje i otvara nosne prolaze.\n" +
                "Spavanje ili odmaranje s uzdignutom glavom poma??e pri za??epljenom nosu.\n\n"

        var tirednessAdvices = "Zdrava rutina spavanja smanjuje iscrpljenost i umor.\n" +
                "Ostanite hidrirani piju??i vodu i jedite hranjive namirnice.\n" +
                "Odmor je vrlo va??an za va??e tijelo jer se bori protiv infekcije. Opu??tanje, disanje i meditacija mogu omogu??iti kvalitetan odmor.\n" +
                "Polako dnevno kretanje odr??ava va??e tijelo i poma??e pri cirkulaciji.\n\n"


        var soreThroatAdvices = "Izbjegavajte boravak u blizini ljudi koji pu??e. Pu??enje ili udisanje dima mo??e pogor??ati bol u grlu.\n" +
                "Va??no je da ostanete dobro hidrirani, stoga pijte puno vode.\n" +
                "Ispiranje grla slanom vodom poma??e pri boli u grlu.\n" +
                "Konzumirajte meku ili rashla??enu hranu, poput sladoleda, te izbjegavajte hranu koja uzrokuje bol prilikom gutanja.\n" +
                "Izbjegavajte alkohol jer on iritira upaljena tkiva grla te dehidrira tijelo.\n\n"

        var temperatureAdvices = "Imate povi??enu temperaturu, testirajte se na COVID-19.\n" +
                "Naj??e????a metoda za sni??avanje temperature je uporaba lijekova za sni??avanje temperature (Paracetamol, Lupocet...). Prije uzimanja lijekova pazite na preporu??eno doziranje, tjelesnu kila??u te uvjete uzimanja tableta.\n" +
                "Najva??nije se dovoljno odmarati. U ve??ini slu??ajeva temperatura postoji zbog infekcije, stoga je tijelu potreban adekvatan odmor za borbu protiv infekcije.\n" +
                "Dobra hidratacija je dobar na??in za regulaciju tjelesne temperature. (Sprje??ava nagli rast temperature)\n" +
                "Tako??er, mo??e biti korisno nositi laganu odje??u, rashladiti ku??u i spavati s laganim pokriva??em.\n\n"

        var hivAdvices = "Pobrinite se da imate dulju zalihu svog lijeka protiv HIV-a. Pitajte svog lije??nika o dobivanju lijeka po??tom.\n" +
                "Razgovarajte sa svojim lije??nikom i provjerite jesu li sva va??a cijepljenja a??urirana, uklju??uju??i cijepljenja protiv sezonske gripe i bakterijske upale plu??a. Ove bolesti koje se mogu sprije??iti cijepljenjem poga??aju osobe s HIV-om vi??e od ostalih.\n" +
                "Kada je to mogu??e, dr??ite se lije??ni??kih termina.\n" +
                "Ako se razbolite, ostanite u kontaktu telefonom ili e-po??tom s ljudima koji Vam mogu pomo??i.\n" +
                "Ukoliko sumnjate da imate simptome COVID-19, nazovite svog lije??nika. Va??no je da nastavite uzimati lijek protiv HIV-a kako Vam je propisano jer to ??e Vam pomo??i da Va?? imunolo??ki sustav bude zdrav.\n\n"

        var diabetesAdvices = "Nastavite uzimati svoje lijekove, uklju??uju??i inzulin, kao i obi??no.\n" +
                "Testirajte i pratite razinu ??e??era u krvi.\n" +
                "Pobrinite se da imate barem 30-dnevnu zalihu lijekova za dijabetes, uklju??uji??i inzulin.\n" +
                "Razgovarajte s lije??nikom ako imate kakvih nedoumica u vezi dijabetesa i COVID-19.\n\n"

        var weakImmuneSystemAdvices = "Ukoliko sumnjate na simptome COVID-19, testirajte se.\n" +
                "Ako imate simptome COVID-19 ili se ne osje??ate dobro, a lije??ite se od raka ili raka koji utje??e na va?? imunolo??ki sustav, obratite se svom lije??niku.\n" +
                "Provjerite jeste li primili sva cjepiva za koja imate pravo kako bi smanjili rizik i kako bi simptomi u slu??aju bolesti bila bla??i.\n" +
                "Nastavite uzimati lijekove i nemojte mijenjati svoj plan lije??enja bez razgovora sa svojim lije??nikom.\n\n"

        var pregnancyAdvices = "Nazovite Va??eg doktora! On ??e Vas savjetovati ??to u??initi i s njime mo??ete razgovarati oko va??ih nedoumica!\n" +
                "Blage simptome mo??ete ubla??iti odmaranjem i unosom puno teku??ine.\n"+
                "Prije uzimanja bilo kojeg lijeka, uklju??uju??i i lijekove protiv bolova, provjerite sa svojim ljekarnikom ili lije??nikom op??e prakse je li prikladan.\n\n"

        var highRiskWorkplaceAdvices = "Rade??i na mjestu s ve??om izlo??enosti COVID-19, preporu??eno je biti u toku s preporu??enim dozama protiv COVID-19 kako bi za??titili sebe i svoje bli??nje.\n" +
                "Nosite masku svaki put kada ste u blizini drugih, uklju??uju??i i va?? dom.\n" +
                "Pratite znakove i simptome bolesti COVID-19 punih 10 dana nakon posljednjeg izlaganja.\n\n"

        var oldAgeAdvices = "Va??no je biti u toku s cjepivima protiv COVID-19.\n" +
                "Pridr??avajte se preventivnih mjera za COVID-19.\n" +
                "Pridr??avajte se uobi??ajenih ili zakazanih termina pregleda kod lije??nika.\n" +
                "Ukoliko sumnjate da imate simptome COVID-19, izolirajte se.\n" +
                "Razmislite o dostavi namirnica i drugih bitnih stvari.\n" +
                "Izbjegavajte javna okupljanja i putovanja koja nisu nu??na.\n" +
                "Odr??avati dobru higijenu te prakticirati fizi??ko  distanciranje dr??e??i se 1.5m od drugih ljudi kada je to mogu??e i izbjegavati kontakt s drugim ljudima.\n\n"

        var downSyndromeAdvices = "Osobe s Downovim sindromom koje imaju nepotpuno razumijevanje svog zdravlja mo??da ??e trebati podr??ka kako bi se izbjegla zaraza ili upravljanje virusom ako ga dobiju.\n" +
                "Pojedinci s Downovim sindromom mo??da nisu u mogu??nosti jasno izraziti svoju bol, nelagodu ili osje??aj uzrujanosti.\n" +
                "Ukoliko primjetite da osoba ima temperaturu, ka??alj ili kratko??u daha, treba prijaviti zdravstvenom djelatniku ??to je prije mogu??e da se mo??e napraviti rana procjena bolesti.\n" +
                "Ukoliko osoba s Downovim sindromom ima blage simptome, treba ostati kod ku??e te nazvati doktora za savjet.\n\n"

        var lungDiseaseAdvices = "Ako imate pogor??anje plu??ne bolesti ili imate nove simptome koji vas mu??e ??? nazovite svog lije??nika i zajedno smislite plan.\n" +
                "Nastavite uzimati propisane lijekove prema uputama. Razmotrite mogu??nosti naru??ivanja lijekova putem po??te za.Ako Vam osiguranje dopu??ta, osigurajte 90-dnevnu zalihu lijekova na recept.\n\n"

        var chronicKidneyOrLiverDisease = "Zna??ajno smanjiti broj ambulantnih pregleda.\n" +
                "Preglede ili konzultacije u??initi na daljinu kad god je to mogu??e i opravdano.\n" +
                "Planirati vrijeme pregleda, smanjiti vrijeme provedeno u ??ekaonici, paziti na adekvatan razmak u slu??aju da se u ??ekaonici nalazi vi??e ljudi.\n" +
                "Ograni??iti broj osoba u pratnji.\n" +
                "Nastavite uzimati bilo koji lijek kako je propisano.\n" +
                "Nu??no je provesti adekvatan epidemiolo??ki probir, te ovisno o nalazu probira, postupiti po naputcima lokalne epidemiolo??ke slu??be.\n\n"

        var cardiovascularDiseaseAdvices = "Smanjite broj ljudi koji se susre??u s pacijentom.\n" +
                "Izbjegavajte bliski kontakt ukoliko je mogu??e.\n" +
                "Nastavite uzimati bilo koji lijek kako je propisano.\n" +
                "Razgovarajte s lije??nikom kako biste utvrdili potrebu za osobnom ili virtualnom pregledom.\n"+
                "Nosite osobnu za??titnu opremu kada je to potrebno.\n\n"


        goBackFromPoll.setOnClickListener {
            startActivity(Intent(this,Account::class.java))
            finish()
        }

        savePoll.setOnClickListener {

                val selectedDifficultyBreathing = difficultyBreathing.checkedRadioButtonId
                val difficultyBreathingButton = findViewById<RadioButton>(selectedDifficultyBreathing)

                val selectedMobility = mobility.checkedRadioButtonId
                val mobilityButton = findViewById<RadioButton>(selectedMobility)

                val selectedChestPain = chestPain.checkedRadioButtonId
                val chestPainButton = findViewById<RadioButton>(selectedChestPain)

                val selectedLossOfSense = lossOfSense.checkedRadioButtonId
                val lossOfSenseButton = findViewById<RadioButton>(selectedLossOfSense)

                val selectedCough = cough.checkedRadioButtonId
                val coughButton = findViewById<RadioButton>(selectedCough)

                val selectedHeadache = headache.checkedRadioButtonId
                val headacheButton = findViewById<RadioButton>(selectedHeadache)

                val selectedSoreThroat = soreThroat.checkedRadioButtonId
                val soreThroatButton = findViewById<RadioButton>(selectedSoreThroat)

                val selectedTiredness = tiredness.checkedRadioButtonId
                val tirednessButton = findViewById<RadioButton>(selectedTiredness)

                val selectedBodyAches = bodyAches.checkedRadioButtonId
                val bodyAchesButton = findViewById<RadioButton>(selectedBodyAches)

                val selectedRunnyNose = runnyNose.checkedRadioButtonId
                val runnyNoseButton = findViewById<RadioButton>(selectedRunnyNose)

                val selectedNausea = nausea.checkedRadioButtonId
                val nauseaButton =  findViewById<RadioButton>(selectedNausea)

                if(temperature.text.isNullOrEmpty()){
                    Toast.makeText(baseContext, "Molimo unesite temperaturu!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("temperature").setValue(temperature.text.toString().toDouble())

                if(difficultyBreathing.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite imate li pote??ko??e s disanjem!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("difficultyBreathing").setValue(difficultyBreathingButton.text.toString())

                if(mobility.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite imate li pote??ko??e s kretanjem!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("mobility").setValue(mobilityButton.text.toString())

                if(chestPain.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite imate li bolove u prsima!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("chestPain").setValue(chestPainButton.text.toString())

                if(lossOfSense.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite jeste li izgubili osjet njuha ili okusa!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("lossOfSense").setValue(lossOfSenseButton.text)

                if(cough.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite imate li ka??alj!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("cough").setValue(coughButton.text.toString())

                if(headache.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite imate li glavobolju!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("headache").setValue(headacheButton.text.toString())

                if(soreThroat.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite imate boli li vas grlo!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("soreThroat").setValue(soreThroatButton.text.toString())

                if(tiredness.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite osje??ate li se iscrpljeno!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("tiredness").setValue(tirednessButton.text.toString())

                if(bodyAches.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite osje??ate li bolove u tijelu!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("bodyAches").setValue(bodyAchesButton.text.toString())

                if(runnyNose.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite je li vam za??epljen nos!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("runnyNose").setValue(runnyNoseButton.text.toString())

                if(nausea.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo ozna??ite osje??ate li mu??ninu!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("nausea").setValue(nauseaButton.text.toString())




            database.child(currentUserID).get().addOnSuccessListener{
                var dayBefore = dayCounter - 1

                if(it.child("Diseases").child("HIV").value.toString() == "true")
                    allAdvices += hivAdvices

                if(it.child("Diseases").child("Cancer").value.toString() == "true" ||
                    it.child("Diseases").child("Transplant").value.toString() == "true" ||
                    it.child("Diseases").child("Weak immune system").value.toString() == "true")
                    allAdvices += weakImmuneSystemAdvices

                if(it.child("Diseases").child("Chronic kidney disease").value.toString() == "true" ||
                    it.child("Diseases").child("Chronic liver disease").value.toString() == "true")
                    allAdvices += chronicKidneyOrLiverDisease

                if(it.child("Diseases").child("Cardiovascular disease").value.toString() == "true")
                    allAdvices += cardiovascularDiseaseAdvices

                if(it.child("Diseases").child("Diabetes").value.toString() == "true")
                    allAdvices += diabetesAdvices

                if(it.child("Diseases").child("Lung disease").value.toString() == "true")
                    allAdvices += lungDiseaseAdvices

                if(it.child("Diseases").child("Lung disease").value.toString() == "true" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += "Cijepite se. Budite u tijeku s cjepivima protiv COVID-19 kako biste sprije??ili pojavu te??kih bolesti. Ne zaboravite ni na cijepljenje protiv gripe i upale plu??a!\n\n"

                if(it.child("pregnancy").value.toString() == "Da")
                    allAdvices += pregnancyAdvices

                if(it.child("pregnancy").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += "Strogo se preporu??uje da se cijepite protiv COVID-19 kako biste za??titili sebe i svoju bebu.\n\n"

                if(it.child("highRiskWorkplace").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Da")
                    allAdvices += "Rade??i na mjestu s ve??om izlo??enosti COVID-19, preporuka je da se testirate na COVID-19. Ukoliko vam je test negativan, testirajte se opet nakon par dana.\n" +
                            "Prilikom vra??anja na posao, nastavite nositi preporu??enu osobnu za??titnu opremu.\n" +
                            "Nosite masku svaki puta kada ste u blizini drugih, uklju??uju??i i va?? dom.\n\n"

                if(it.child("highRiskWorkplace").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += highRiskWorkplaceAdvices

                if(it.child("Diseases").child("Down syndrome").value.toString() == "Da")
                    allAdvices += downSyndromeAdvices

                if(it.child("Diseases").child("Down syndrome").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += "Cijepljenje osoba s Down sindromom je osobito va??no!"

                if(it.child("day$dayCounter").child("difficultyBreathing").value.toString() == "Da" ||
                    it.child("day$dayCounter").child("mobility").value.toString() == "Da" ||
                    it.child("day$dayCounter").child("chestPain").value.toString() == "Da")
                    allAdvices += emergencyAdvice

                if(it.child("day$dayCounter").child("lossOfSense").value.toString() == "Da")
                    allAdvices += lossOfSenseAdvice

                if(it.child("day$dayCounter").child("cough").value.toString() == "Da" && it.child("smoking").value.toString() == "Da")
                    allAdvices += "Smanjite ili prestanite pu??iti u potpunosti. Za savjet o prestanku pu??enja posjetite : https://www.zzjzdnz.hr/zdravlje/pusenje-i-zdravlje/180 ili potra??ite stru??nu pomo??."

                if(it.child("day$dayCounter").child("cough").value.toString() == "Da" && it.child("smoking").value.toString() == "Ne")
                    allAdvices += "Poku??ajte izbjegavati boravak u blizini ljudi koji pu??e kako vam se ka??alj ne bi pogor??ao." + coughAdvices

                if(it.child("smoking").value.toString() == "Da")
                    allAdvices += smokerAdvices

                if(it.child("day$dayCounter").child("cough").value.toString() == "Da" &&
                    it.child("day$dayBefore").child("cough").value.toString() == "Da")
                    allAdvices += "Ukoliko Vam ka??alj ne prestane nakon 10 dana, ili se pogor??ka, molimo Vas posjetite doktora.\n"

                if(it.child("day$dayCounter").child("headache").value.toString() == "Da")
                    allAdvices += headacheAdvices

                if(it.child("day$dayCounter").child("headache").value.toString() == "Da" &&
                    it.child("day$dayBefore").child("headache").value.toString() == "Da")
                    allAdvices += "Ukoliko Vam nakon vi??e dana glavobolja ne prestane, ne reagira na lijekove ili se pogor??a, obratite se lije??niku.\n"

                if(it.child("day$dayCounter").child("nausea").value.toString() == "Da")
                    allAdvices += nauseaAdvices

                if(it.child("day$dayCounter").child("runnyNose").value.toString() == "Da")
                    allAdvices += runnyNoseAdvices

                if(it.child("day$dayCounter").child("tiredness").value.toString() == "Da")
                    allAdvices += tirednessAdvices

                if(it.child("day$dayCounter").child("bodyAches").value.toString() == "Da")
                    allAdvices += bodyAchesAdvices

                if(it.child("day$dayCounter").child("soreThroat").value.toString() == "Da")
                    allAdvices += soreThroatAdvices

                if(it.child("day$dayCounter").child("temperature").value.toString().toDouble() > 37.8)
                    allAdvices += temperatureAdvices
                else allAdvices += "Va??a temperatura je normalna.\n\n"

                if(it.child("age").value.toString().toInt() >60)
                    allAdvices += oldAgeAdvices


                database.child(currentUserID).child("day$dayCounter").child("preporuke").setValue(allAdvices)
            }


            database.child(currentUserID).child("dayCounter").setValue(dayCounter+1)

            startActivity(Intent(this,Account::class.java))
            finish()
        }



    }
}