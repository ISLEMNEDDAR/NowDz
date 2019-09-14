package com.example.nowdz.controller

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.example.nowdz.R
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.model.Source
import com.example.nowdz.viewModel.ArticleViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object ArticleController {
        private lateinit var articleViewModel: ArticleViewModel
            private var khabar : Source = Source(
                name = "الخبر",
                urlJourrnal = "https://www.elkhabar.com/",
                logo=R.drawable.khabar_logo
            )
            private var soir : Source = Source(
                name="Le Soir",
                urlJourrnal ="https://www.lesoirdalgerie.com/",
                logo=R.drawable.soir_logo
            )
            private var liberte : Source = Source(
                name = "La Liberté",
                urlJourrnal="https://www.liberte-algerie.com/",
                logo=R.drawable.liberte_logo

            )
            private var echourouk : Source = Source(
                name ="الشروق" ,
                urlJourrnal="https://www.echoroukonline.com/",
                logo=R.drawable.echourouk_logo

            )
            private var article0: Article = Article(
                journal = khabar,
                author = "ل.ب",
                titre = "توقيف حفيد جمال ولد عباس",
                url="",
                urltoImage = R.drawable.article0_img,
                published_at= Date(2019, 6, 14, 13, 0),
                content = "أفادت مصادر مؤكدة لـ\"الخبر\" أن مصالح أمن ولاية عين تموشنت أوقفت يوم الخميس، أحد أحفاد الأمين العام السابق لحزب جبهة التحرير الوطني، والبالغ من العمر 30 سنة، خلال الأحداث التي شهدتها هذه المدينة في الأيام الأخيرة، خلال الاحتجاجات التي عرفتها بعد وفاة الشاب خير الدين عبيد، المنتحر حرقا بعد حجز سلعته وميزانه من طرف الشرطة.\n" +
                        "\n" +
                        "وأفادت ذات المصادر أن حفيد جمال ولد عباس، الموجود حاليا رهن الحجز تحت النظر، عند الشرطة، ألقي عليه القبض بعد أن \"شوهد وهو يحرض الشباب المحتج على التخريب\".\n" +
                        "\n" +
                        "يذكر أن المعني، وهو الذي يحمل اسم جده، مسبوق قضائيا، وسبق وأن تم القبض عليه مع مجموعة أشرار نظمت عملية سطو على محل للمجوهرات في مدينة أرزيو بولاية وهران، حين كان جده وزيرا للتضامن الوطني. وقد أحيل حينها كل المقبوض عليهم في تلك القضية أمام محكمة الجنايات لدى مجلس قضاء وهران، باستثنائه هو، والذي تم \"إخراجه من الملف الجنائي في مجلس قضاء وهران\". كما أفادت ذات المصادر أنه \"مسبوق في العديد من القضايا أمام محكمة عين تموشنت\".",
                categorie = R.string.politics,
                suivi = false
            )

            private var article1: Article = Article(
                journal = khabar,
                titre = "الخضر يتقدم بمركزين",
                urltoImage = R.drawable.article_image,
                url="",
                content = "ارتقى المنتخب الوطني الجزائري لكرة القدم بمركزين اثنين، في التصنيف الشهري الذي نشره الاتحاد الدولي لكرة القدم \"فيفا\" اليوم الجمعة عبر موقعه الرسمي على شبكة الانترانت . \n" +
                        "وبمجموع 1346 نقطة, يحتل المنتخب الوطني المركز ال12 (+1)، على الصعيد الإفريقي، بعيدا عن المنتخب السنغالي متصدر المنتخبات الإفريقية في هذا التصنيف (المركز ال22)، و احد منافسي \"الخضر\" في كاس أمم إفريقيا لكرة القدم المقررة بمصر (من 21 يونيو إلى 19 يوليو ). \n" +
                        "ومن جهتهما يحتل المنافسان الآخران للجزائر في الدور الأول من المنافسة الإفريقية، كينيا وتانزانيا على التوالي المركزين 105 (+3) و 131 (دون تغيير)، فيما احتل منتخب مالي الذي سيلتقي الخضر وديا يوم الأحد المقبل بالدوحة القطرية المركز ال62 (+3) . \n" +
                        "وعلى الصعيد العالمي بقي المنتخب البلجيكي في الصدارة بمجموع (1746نقطة ) متقدما على كل من فرنسا في المركز الثاني ب (1718نقطة) والبرازيل في المركز الثالث ب(1681نقطة ) ثم انجلترا في المركز الرابع ب (1652 نقطة ). \n" +
                        "وسيصدر التصنيف المقبل للاتحاد الدولي يوم 25 يوليو المقبل.",
                categorie = R.string.sport,
                published_at = Date(2019, 6, 14, 12, 20),
                author = "ف.ن/ وأج",
                suivi = false
            )

            private var article2: Article = Article(
                journal = khabar,
                titre = "\"دوري في \"أولاد الحلال\" علامة فارقة في مساري الفني\"",
                urltoImage = R.drawable.article2_img,
                content = "تقمصت الممثلة المسرحية فضيلة حشماوي في مسلسل \"أولاد الحلال\"، دور شخصية مركبة تتجلى فيها تناقضات المجتمع، تحمل السبحة بيدها وتتآمر على كنتها. هي والدة تاجر مخدرات، لكنها تعطف على خادمتها، تجسد معركة الخير والشر. اعترفت في حوارها مع \"الخبر\" أن الدور شكّل علامة فارقة في مسارها الفني الذي يمتد إلى أكثر من 45 سنة منذ بداياتها الأولى في المسرح مع الراحل عبد القادر علولة. كما اعتبرت أن سكان الدرب والحي العتيق واللهجة الوهرانية، تقاسمت أدوار البطولة مع بقية الممثلين لتجسيد عمل فني رائع يندرج ضمن دراما الشعب والفئات الشعبية، حسب الرؤية الإخراجية لأعمال نصر الدين السهيلي التي تحاكي قصص المهمشين والفقراء والمحرومين، وملامسة واقع الطبقات المسحوقة. \n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "كيف تجسدت فكرة المشاركة في مسلسل \"أولاد الحلال\" رفقة ثلة من الممثلين الشباب والفنانين المخضرمين، على غرار أحمد بن عيسى؟\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "تلقيت اتصالا من الممثل عبد القادر جريو بحكم معرفتي به في مجال المسرح وعرض علي فكرة المشاركة في المسلسل، بعد أن أقنعني بأنه تحدث مطولا مع المخرج التونسي وأجمعا على أنني الأجدر بتقمص الدور، في مسلسل يوظف اللهجة الوهرانية كلغة أساسية في الحوار لإضفاء لمسة محلية على العمل الدرامي. تجسدت الفكرة خلال لقاء لعرض الشبكة البرامجية الجديدة لسنة 2019. تعرفت على المنتج وتابعت بعض لقطات المسلسل، وعند عودتي إلى وهران تلقيت اقتراحا لتقمص دور آخر عوض الدور الأول المقترح علي من طرف المخرج، الذي رأى من خلال الكاستينغ أن شخصية \"عايشة\" القوية والمركبة والمعقدة والمحورية في سيناريو المسلسل، يتطلب إمكانيات أكبر، لذا فكر في منحي الدور، لأن هناك بعض الأدوار تحمل الممثل وأخرى يحملها الممثل على عاتقه ويساهم في إنجاحها.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "لاحظنا من خلال الأداء التناقضات الكبيرة التي يحملها دور \"عايشة\"، وهي خليط بين الشر والخير وعلامات التدين والتكيف مع عمل ابنها في مجال المخدرات، دون أدنى تأنيب ضمير، فكيف استطعت التحكم في الدور؟\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        " صحيح، \"عايشة\" امرأة متسامحة وقاسية في الوقت نفسه، وهو أمر صعب التجسيد فعلا، لكنني كممثلة مطالبة بأداء كل الأدوار. فيما يخص الشخصية، فهي امرأة مستعدة لكل شيء من أجل أولادها، والنموذج موجود بكثرة في مجتمعنا الجزائري المملوء بمظاهر النفاق والصراع بين العجوز والكنة. صراحة، تمنيت أداء مثل هذه الأدوار التي تجعل الفنان ينتقل من حالة إلى حالة مغايرة تماما.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "هل أداء شخصية شريرة انعكس على حب الجمهور لفضيلة حشماوي المتعودة على الحياة البسيطة مع سكان مدينة وهران؟\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "في الحقيقة الناس كرهوا الدور في الوهلة الأولى، لكنهم مدركون بأنه مجرد تمثيل ولا علاقة له بشخصية فضيلة في الواقع المعروفة بأنها امرأة شعبية تشارك في المسيرات والحراك، وكلهم \"يسلموا على راسي\"، وحتى زملائي الممثلين في المسرح والمخرجين فرحوا لنجاحي في الدور. أتذكر مقولة للممثل المصري الكبير محمود الميليجي الذي علّق على كره الناس له بسبب أدائه لأدوار شريرة، وقال في استجواب \"أنا لا أستطيع ذبح دجاجة\"، والأمر نفسه ينطبق على فضيلة. فيما النكت الطريفة أتذكر أنني في رمضان وجدت نفسي عالقة وسط الازدحام المروري وتركت السيارات تمر، وعندما رآني أحد رجال الشرطة، خاطبني قائلا \"الحاجة عايشة عاقلو ڤاع كيما هاك\"، في إشارة إلى تناقض بين الدور والواقع.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "أداؤك الجيد في المسلسل، على غرار كل الممثلين حتى الهواة منهم الذين شاركوا لأول مرة، أبهر كل المتتبعين وأجمع المختصون على لمسة المخرج التونسي نصر الدين السهيلي في توجيه الممثلين على بلاطو التصوير، ما مدى صحة هذا الأمر؟\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "أعترف، رغم أن مساري يمتد منذ سنة 1974 بالمشاركة في عدد لا يحصى من المسرحيات رفقة العملاق عبد القادر علولة، وحوالي عشرين عمل تلفزيوني وثلاثة أعمال سينمائية، أن السهيلي تمكن من إخراج الأحسن من كل الممثلين، فتحية كبيرة له ولكل فريق الإخراج حتى التقنيين وطريقة كتابة النص والحوارات والإخراج القوي. والمخرج كان يشتغل مع كل ممثل ويأخذ الوقت الكافي لشرح ما يريده من اللقطة، دون إهمال الجزئيات الصغيرة. كما أن الخبرة المكتسبة مع الراحل علولة سهلت من مهمتي، بحيث كان يقول لنا تقمصوا الشخصية بكل حرية، مع التعبير الجسدي. صراحة، ارتحنا كثيرا مع نصر الدين لدرجة أن بعض الممثلين تحملوا لقطات عنف وضرب بكل أريحية. كما أنوّه بدور المنتج والفريق التقني في توفير أحسن الظروف لأداء الدور، وهو ما عشته شخصيا عندما وجدت صعوبة في إتمام التمثيل وفقدت التركيز بسبب التوتر الشديد وضيق الوقت؛ لأنني كنت مطالبة بإنهاء التمثيل داخل الشقة المستأجرة في فترة لا تتعدى عشرة أيام. لكن وقوف فريق الإنتاج والتقنيين إلى جانبي، ساعدني على تجاوز الأمر والعودة إلى التمثيل. أستعيد دائما طريقة تعامل علولة مع الممثلين وملازمته لغاية إتقان الدور وإعطائه ما يريده منه.",
                categorie = R.string.culture,
                published_at = Date(2019, 6, 11),
                author = "جعفر بن صالح",
                url = "",
                suivi = false
            )

            private var article3: Article = Article(
                journal = khabar,
                titre = "جلسة لمجلس الأمن بعد استهداف سفينتين في الخليج",
                urltoImage = R.drawable.article3_img,
                content = "أبلغت واشنطن مجلس الأمن الدولي أنها ستبحث مسألة استهداف الناقلتين في الخليج في جلسة مغلقة اليوم الخميس.\n" +
                        "\n" +
                        "وأعلن جوناثان كوهين السفير الأمريكي لدى الأمم المتحدة أن واشنطن تخطط لبحث الهجمات على ناقلتي النفط في خليج عمان في جلسة مغلقة لمجلس الأمن الدولي تعقد في وقت لاحق من اليوم الخميس.\n" +
                        "\n" +
                        "وقال كوهين: \"من غير المقبول أن يهاجم أي طرف الشحن التجاري\"، مضيفا أن \"هجمات اليوم على السفن في خليج عمان خطيرة جدا وتثير المخاوف.. \"الحكومة الأمريكية تقدم المساعدة وستواصل تقييم الوضع.\"\n" +
                        "\n" +
                        "واستهدف هجوم مجهول اليوم الخميس ناقلتي نفط خلال مرورهما عبر خليج عمان.\n" +
                        "\n" +
                        "ووقع حادث اليوم بعد نحو شهر من استهداف أربع سفن تجارية بأعمال تخريب مماثلة أمام سواحل ميناء الفجيرة الإماراتي، وألقت الولايات المتحدة باللوم على إيران في ذلك الحادث.\n" +
                        "\n" +
                        "وتضررت جراء حادث اليوم الناقلتان اللتان تحملان علمي جزر مارشال وباناما على التوالي، وأعلنت وزارة التجارة اليابانية أنهما تحملان شحنة على صلة باليابان.\n" +
                        "\n" +
                        "وأفادت وكالة الأنباء الإيرانية الرسمية \"إرنا\" بإجلاء 44 بحارا من متن الناقلتين المنكوبتين القادمتين من قطر والسعودية.\n" +
                        "\n",
                categorie = R.string.international,
                published_at = Date(13, 6, 2019, 12, 20),
                author = "إ.ب/رويترز",
                url="",
                suivi = false
            )

            private var article4: Article = Article(
                journal = soir,
                titre = "Ouyahia, la chute !",
                urltoImage = R.drawable.article4_img,
                content = "Les pronostics qui tablaient sur l’incarcération d’Ahmed Ouyahia se sont vu donner raison hier : l’ancien Premier ministre a passé sa première nuit à la prison d’El-Harrach où sont écroués les hommes d’affaires qui ont précipité sa chute ainsi que celle de Abdelghani Zaâlane, également placé sous mandat de dépôt.\n" +
                        "Abla Chérif - Alger (Le Soir) - L’information a été rendue publique vers 17 h 30, soit trois heures et demie après l’arrivée d’Ouyahia à la Cour suprême pour être entendu sur des faits très graves, ne cessaient de répéter les spécialistes depuis l’annonce de sa convocation par cette haute instance.\n" +
                        "Ces faits sont tous liés de manière directe aux dossiers de ces hommes d’affaires mis sous mandat de dépôt par des tribunaux civils d’Alger et poursuivis sur la base de nombreux chefs d’inculpation. Il s’agit principalement de Ali Haddad, ancien patron du FCE et propriétaire du groupe ETRHB, et de Mahieddine Tahkout, patron de l’usine de montage de véhicules Hyundai incarcéré lundi avec deux de ses frères associés et son fils.\n" +
                        "Comme Haddad, Tahkout est soupçonné d’avoir bâti sa fortune grâce aux relations privilégiées qu’il entretenait avec les Bouteflika et les hommes forts du moment. Ouyahia est, lui, accusé d’être l’un des responsables principaux des passe-droits et avantages outranciers dont ont bénéficié ces oligarques.\n" +
                        "Des sources bien informées affirmaient, en effet, hier que le procureur de la Cour suprême l’a inculpé sur la base de plusieurs charges : passations de contrats de manière illégale, octroi de marchés avantageux, abus de fonction et privilèges à des groupes et personnes. Au cours des semaines précédentes, l’ancien Premier ministre avait été auditionné à trois reprises par des juges qui l’ont écouté sur la base des déclarations faites par des oligarques. La dernière en date remonte à ce début de semaine.\n" +
                        "Dimanche, il a comparu en même temps que Tahkout et 56 autres cadres au tribunal de Sidi-M’hamed. Son audition a duré plus de dix heures (il est sorti vers 23 h), laissant deviner que la suite des évènements allait s’accélérer pour lui. Quelques heures après la mise sous mandat de dépôt de l’homme d’affaires que l’on disait être très proche de l’ancien Premier ministre, ainsi que dix-neuf autres personnes, l’opinion apprenait effectivement que la Cour suprême avait convoqué Ouyahia et Abdelghani Zaâlane, ancien ministre des Transports et des Travaux publics et chef de campagne de Bouteflika pour son cinquième mandat.\n" +
                        "Le tapage médiatique qui s’en est suivi mais également l’insistance des spécialistes à affirmer que les charges qui pesaient sur l’homme le plus impopulaire d’Algérie laissaient quelque peu deviner l’issue que devait prendre l’évènement, mais une polémique s’est installée au sein de ces mêmes experts parmi lesquels certains jugeaient la Cour suprême incompétente pour la gestion de dossiers d’accusation d’anciens hauts responsables compte tenu de l’existence d’une disposition constitutionnelle imputant cette charge à une juridiction spéciale, une haute cour, mais dont la mise en place n’a jamais vu le jour dans les faits.\n" +
                        "Une certitude, Ouyahia savait qu’en entrant à la Cour suprême, il effectuait son dernier déplacement d’homme libre puisqu’il s’est très vite aperçu de la présence d’un fourgon cellulaire garé à l’intérieur, affirment des sources bien au fait des évènements qui se sont déroulés. Il n’en a pas été de même pour Abdelghani Zaâlane.\n" +
                        "Arrivé vers 15h30, l’ex-directeur de campagne de Bouteflika est ressorti trois heures plus tard à bord de son véhicule personnel, après s’être vu signifier son placement sous contrôle judiciaire. Au cours des heures et des jours suivants, d’autres personnalités sont appelées à comparaître elles aussi devant de le procureur de la Cour suprême.\n" +
                        "Le 26 mai dernier, cette dernière avait publié un communiqué comportant une liste de noms de douze anciens ministres et hauts responsables. Zaâlane et Ouyahia y figuraient. Les convocations concernent à présent Abdelmalek Sellal, Amar Tou, Boudjema Talai, Karim Djoudi, Amara Benyounès, Amar Ghoul, Abdelkader Bouazghi, Abdeslam Bouchouareb, Abdelkader Zoukh et le wali d’El-Bayadh.\n" +
                        "Hier, un communiqué émanant de la justice a annoncé qu’une demande de levée de l’immunité parlementaire de Boudjema Talai et de Amar Ghoul avait été déposée.\n" +
                        "A. C.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Scènes de liesse aux abords de la Cour suprême et de la prison d’El-Harrach",
                categorie = R.string.international,
                published_at = Date(13, 6, 2019, 11, 0),
                author = "Abla Chérif",
                url = "",
                suivi = false
            )

            private var article5: Article = Article(
                journal = soir,
                titre = "Belkebla, l’autre face cachée des Verts",
                urltoImage = R.drawable.article5_img,
                content = "A peine deux jours à Doha qu’un nouveau scandale frappe aux portes des Verts de Djamel Belmadi déjà fortement «défoncées» par le cas Atal signalé en mars dernier mais révélé publiquement à la veille du départ de la sélection en stage précompétitif de la CAN-2019 au Qatar.\n" +
                        "L’EN de football est-elle devenue maudite qu’il faudrait procéder à sa dissolution pour ne pas s’encombrer d’autres farces qui sont légion dans la pratique du jeu à onze en Algérie ? Chicha, cocaïne, mœurs et autres «excès» qui ne sont pas le propre des seuls sportifs, sont des scandales qui ont marqué l’ambiance «moderne» des Verts. Ce n’est pas que les sélections des années 1970, 80 et 90 n’ont pas connu ce genre d’histoires parfois tolérées, gérées pour certains cas, souvent ignorées et dissimulées du grand public, mais qui ont fini avec le temps à affecter la réputation et de l’EN et des éléments qui sont à l’origine de ces erreurs de jeunesse. C’est tellement facile de nos jours de sanctionner les auteurs de tels actes qui nuisent à la discipline du groupe. Les preuves sont là. Implacables. \n" +
                        "Les sanctions si exemplaires, si punitives qu’elles soient n’empêchent pourtant pas les récidives. Avant Belkebla il y a eu Boudebouz et avant Boudebouz il y a d’autres affaires du genre, parfois autrement plus gravissimes, dont les auteurs sont aujourd’hui de bons pères de famille. Pour dire que l’écart de Haris Belkebla est un éternel recommencement. Le joueur de Reims qui n’est plus apparu dans les radars des sélectionneurs algériens depuis le match contre l’Argentine aux JO de Rio-2016 (il était suspendu lors du 3è match du premier tour contre le Portugal) a été sélectionné pour la CAN-2019 à la grande surprise de tous. Belmadi l’a retenu alors que d’autres éléments évoluant aussi bien en Algérie qu’à l’étranger avaient le profil pour remplacer les Taïder (choix technique), Bentaleb et Chita (blessés et opérés) ainsi que Mehdi Abeid pas totalement remis de sa blessure et qui manquait de compétition (il n’a disputé que les 5 dernières minutes du play-off entre Dijon, son club, et Lens, où il a été formé, et ce, après deux mois d’inactivité due à une blessure contractée en sélection contre la Tunisie, le 26 mars dernier). Le Brestois qui a certes réussi l’accession avec son club en Ligue 1 Conforama n’était pas le médian tant espéré par Belmadi pour combler les lacunes de la sélection dans ce compartiment. Le sélectionneur national s’est peut-être rappelé de l’ancien Tourangeau dès lors que le Stade Brestois a réussi l’exploit de remonter parmi l’élite après six années de purgatoire en comptant sur son milieu algérien auteur de bonnes statistiques (36 matchs joués dont 33 en Ligue 2, 4 buts marqués et seulement 8 cartons jaunes reçus). Son entraîneur à Brest, Jean-Marc Furlan, dit de lui que c’est un joueur précieux. Dans un entretien à nos confères de Le Buteur, l’ancien coach de l’ESTAC où évoluaient d’autres ex-internationaux algériens dont Rafik Saïfi, Farid Ghazi, Karim Ziani, Mehdi Meniri et Mohamed Berradja a ainsi résumé le profil de Belkebla. «Ce qui est intéressant chez Haris c’est sa grande récupération et un très grand volume de jeu. Pour moi sincèrement, c’est le vrai footballeur, qui a beaucoup d’endurance et qui connaît et adore ce sport. Il possède tout ce qu’on aime dans un milieu de terrain moderne. Une bonne récupération de balles et une très bonne lecture du jeu», confiait-il. Et de préciser à la question de la sélection surprise de son joueur : «Belkebla a cette personnalité qui peut lui permettre de vite s’intégrer. Maintenant, techniquement, tactiquement et sur le plan stratégique, c’est vraiment un très bon élément. Sur le plan aérobie, c’est un marathonien. C’est aussi très important pour un milieu de terrain». Et de montrer cette autre facette du joueur qui peut surprendre à la lumière de ce qui s’est passé à Doha. «Je peux vous dire que c’est une belle personne. C’est un garçon très bien éduqué et très agréable à vivre dans le vestiaire et en dehors. C’est aussi important pour un entraîneur», a-t-il indiqué. ",
                categorie = R.string.sport,
                published_at = Date(13, 6, 2019, 11, 0),
                author = "Mohamed Bouchama",
                url = "",
                suivi = false
            )

            private var article6: Article = Article(
                journal = soir,
                titre = "«Par Azar», une belle rencontre avec l’art",
                 urltoImage= R.drawable.article6_img,
                content = "Chaque œuvre raconte une histoire ! Certaines, comme Jardin des femmes, sont tellement longues qu’elles peuvent remplir un livre. \n" +
                        "\n" +
                        "En 2017, la jeune artiste et poétesse Azar rencontre une femme au festival itinérant Racont’Arts qui s’est déroulé à Aït Ouabane, en Kabylie, cette année-là.  Cette villageoise a beaucoup de choses à raconter. Son histoire et son visage se retrouvent aujourd’hui dans le tableau Jardin des femmes de l’exposition de dessins «Par Azar», de  l’artiste Azar à la galerie d’art algéroise Ifru Design.\n" +
                        "Autodidacte, Kenza Djouama, de son nom d’artiste Azar, a commencé son chemin artistique dans la peinture à l’huile, elle qui était déjà passionnée par la littérature, la poésie et le surréalisme, ce mouvement aussi bien littéraire qu’artistique. Née dans une famille d’artistes, cette enfant du quartier algérois côtier, Bologhine, avait, dès l’enfance, connu et vécu l’art à travers, notamment, la voix de chanteuse et la poésie de sa mère, sa première source d’inspiration. De sa mère, elle a aussi hérité l’amour de la nature.\n" +
                        "Enfant de la ville, Azar, dont le nom signifie «racine»  en langue amazighe, aimait toutefois fuir le milieu urbain et ses bruits pour se réfugier dans la peinture. Avec le temps, elle découvre aussi le dessin à l’encre de Chine. \n" +
                        "«Le besoin que je ressens de dessiner des branches et des racines est vital ; la femme et la vie, j’aime les honorer dans mes dessins», explique-t-elle. Dans ses œuvres à la galerie Ifru Design, l’homme, surtout la femme, sont souvent en fusion avec les racines et les arbres, comme un prolongement naturel l’un de l’autre. Les dessins réalisés à l’encre de Chine (noir) sur du blanc, sont enjolivés de rose ou d’un camaïeu de couleurs. Comme elle nous l’avait expliqué elle-même lors du vernissage, cette exposition est comme un «retour aux racines». C’est aussi, pour Azar, un hommage à l’Algérie, son pays, son histoire, sa culture et à la femme algérienne, source de tant de fierté éternelle.  \n" +
                        "Les œuvres d’Azar sont vivantes ! «Renaissance des esprits quand parle le dessin», nous dit un extrait du poème accompagnant cette très belle exposition. Chaque rencontre laisse une impression. «J’aime conserver les belles âmes par l’encre et le papier.»\n" +
                        "L’expo  «Par Azar», à la galerie Ifru Design au Télemly, restera ouverte jusqu’au 19 juin 2019, au bonheur des belles âmes.",
                categorie = R.string.culture,
                published_at = Date(13, 6, 2019, 11, 0),
                author = "Kader Bakou",
                url = "",
                suivi = false
            )

            private var article7: Article = Article(
                journal = liberte,
                titre = "Un joyau aux innombrables potentialités écoculturelles",
                urltoImage = R.drawable.article7_img,
                content = "Formé d’un enchaînement de canyons de grès, de gueltas et de nombreux sites de peintures rupestres, le site, jusque-là méconnu malgré la beauté de ses paysages et ses richesses archéologiques, se propose d’offrir des circuits de rêve et de faire vivre aux adeptes du Sahara une traversée désertique inoubliable.\n" +
                        "\n" +
                        "Situé à environ 350 km au nord de Tamanrasset, entre Amguid et Timeskis, le Tassili de l’Immidir jouit des potentialités touristiques qui laissent l’imaginaire de l’individu construire toutes sortes d’images fantasmagoriques. Formé d’un enchaînement de canyons de grès, de gueltas et de nombreux sites de peintures rupestres, le site, jusque-là méconnu malgré la beauté de ses paysages et ses richesses archéologiques, se propose d’offrir des circuits de rêve et de faire vivre aux adeptes du Sahara une traversée désertique inoubliable, rimant avec exploration et découvertes.\n" +
                        "\n" +
                        "C’est du moins ce qui ressort des différentes présentations animées à l’occasion de l’atelier technique qui s’est tenu du 9 au 13 juin, à Moulay Lahcène, dans la commune d’In Mguel (130 km au nord de Tamanrasset). Organisé par la direction nationale du PPCA (projet des parcs culturels algériens), portant conservation de la biodiversité d’intérêt mondial et utilisation durable des services écosystémiques dans les parcs culturels en Algérie, l’atelier auquel ont pris part tous les partenaires du projet se veut ainsi une occasion d’aborder les véritables défis de gestion et de conservation du patrimoine archéologique de l’Immidir afin de parvenir à l'élaboration du profil écoculturel de ce site qui s’étend jusqu’aux fascinantes gorges d’Arak.\n" +
                        "\n" +
                        "L’initiative s’inscrit dans le cadre de l’élargissement des activités de suivi du patrimoine écoculturel, initiées sur les sites prioritaires de Taessa et de Tefedest, a indiqué Narimane Saheb, chargée de la communication auprès de la direction nationale du PPCA, selon qui un travail de titan a été déjà réalisé dans ce sens. Ce travail, précise-t-elle, porte essentiellement sur l’élaboration d’un premier diagnostic du site prioritaire de l’Immidir en “se basant sur une évaluation initiale de la biodiversité et du patrimoine archéologique.\n" +
                        "\n" +
                        "Le recueil d’information servira à mettre les assises de base pour l’élaboration des plans de surveillance et de contrôle ainsi que pour l’orientation des stratégies d’intervention de l’office national du parc culturel de l’Ahaggar sur ce territoire”. D’après notre interlocutrice, cette mission consiste à récolter des données de terrain se rapportant à la diversité biologique du site d’étude en vue de l’élaboration de son profil écoculturel, à même d’initier la méthodologie de relevé liée à la qualité de l’eau des gueltas de l’Immidir. La mission vise aussi à explorer ce patrimoine archéologique avec test de la méthodologie d’inventaire et du suivi de l’état de conservation, notamment des sites de peintures rupestres. Des reportages photographiques et vidéographiques sur la diversité des patrimoines naturels et archéologiques sont aussi prévus par la direction du projet qui compte également mener des enquêtes anthropologiques sur l’occupation et l’exploitation de l’Immidir.\n" +
                        "\n" +
                        "Parlant des résultats de la mission de terrain effectuée dans une partie de ce site prioritaire du 15 février au 3 mars de l’année en cours, on a fait savoir que les informations obtenues ont permis de procéder à une localisation des potentialités à valoriser et à l’identification des contraintes à surmonter. Aussi, un diagnostic initial a été élaboré afin d’aboutir à la connaissance approfondie du territoire et du coup parvenir à déterminer l’ensemble de ses atouts et ses lacunes. Les résultats ont surtout donné lieu à l’identification des acteurs impliqués dans le processus de gestion laissant penser à un management basé sur la diversification des dispositifs communicationnels et informationnels. “La mobilisation des acteurs est incontournable dans la promotion d’un espace naturel et le développement durable.\n" +
                        "\n" +
                        "Tout acteur, institutionnel, non institutionnel, société civile et population locale, participe chacun à sa manière au diagnostic des besoins et à l’optimisation de la connaissance du territoire”, souligne la représentante de la direction nationale du PPCA en mettant l’accent sur l’importance du déploiement à l’échelle locale d’une intelligence collective pour la valorisation patrimoniale et territoriale grâce au croisement des données éco-biologiques et ethnosociologiques. ",
                categorie = "CULTURE",
                published_at = Date(13, 6, 2019, 11, 0),
                author = "Rabah Kareche",
                url = "",
                suivi = false
            )

            private var article8: Article = Article(
                journal = liberte,
                titre = "Reprise du dialogue au Soudan",
                urltoImage = R.drawable.article8_img,
                content = "La pression internationale, avec les médiations éthiopienne et américaine, et le mouvement de la désobéissance civile ont fini par faire plier les militaires soudanais au pouvoir.\n" +
                        "\n" +
                        "Le dialogue entre les représentants de la contestation au Soudan et les militaires au pouvoir reprendra incessamment, conformément à l’accord conclu mardi soir, sous médiation éthiopienne, pour la fin du mouvement de désobéissance civile et la reprise du travail hier, après plus de trois jours de quasi-paralysie de la capitale. De son côté, le conseil militaire de transition a accepté, “dans un geste de bonne volonté” de “libérer tous les détenus politiques”, a indiqué le représentant du Premier ministre éthiopien Abiy Ahmed, Mahmoud Drir.\n" +
                        "\n" +
                        "Il a également déclaré que “l’ALC a accepté de mettre fin au mouvement de désobéissance civile à partir d’aujourd'hui (hier soir)” et “les deux parties ont accepté de reprendre prochainement les discussions”, sans pour autant en donner la date exacte. Rappelons que les pourparlers entre les deux camps ont été suspendus unilatéralement par l’armée le 20 mai, après l’échec de la réunion consacrée à la composition de la future instance de transition, censée diriger le pays pendant trois ans. Le diplomate éthiopien a souligné, dans des déclarations rapportées par le média soudanais al-Shorooq, qu’il s’attellera à “éviter que les deux parties versent dans l’escalade verbale à travers des accusations réciproques”, afin d’“avancer sérieusement dans notre médiation”, tout en se disant “confiant que le Soudan dépassera cette période difficile”. À noter que le Conseil de sécurité des Nations unies a condamné fermement mardi soir la violence au Soudan et a appelé les militaires au pouvoir et le mouvement de contestation à œuvrer pour trouver une issue à la crise.\n" +
                        "\n" +
                        "Dans un communiqué, les membres du Conseil ont demandé la fin immédiate des violences contre les civils et souligné l’importance du respect des droits humains. L’annonce de la reprise des négociations intervient alors que les meneurs de la contestation avaient annoncé, lundi soir, qu’ils publieraient prochainement la composition de leur propre instance dirigeante avec un Premier ministre. Elle intervient également au lendemain de l’annonce de la venue cette semaine du secrétaire d’État américain adjoint chargé de l’Afrique, Tibor Nagy. Par ailleurs, un groupe d’experts en droits humains de l’ONU a demandé hier une enquête onusienne sur les violences commises au Soudan contre des “manifestants pacifiques” qui demandaient le départ des généraux au pouvoir depuis la chute du président Omar al-Bachir. “Compte tenu de l’ampleur et de la gravité des violations des droits humains signalées et de la nécessité d'agir rapidement pour empêcher une nouvelle escalade, nous demandons au Conseil des droits de l'homme d’ouvrir une enquête indépendante sur les violations (...) au Soudan”, ont déclaré ces cinq experts dans un communiqué.",
                categorie = "INTERNATIONAL",
                published_at = Date(13, 6, 2019, 11, 0),
                author = "Merzak Tigrine",
                url = "",
                suivi = false
            )

            private var article9: Article = Article(
                journal = echourouk,
                titre = "يداع الجنرال المتقاعد علي غديري الحبس المؤقت",
                urltoImage = R.drawable.article9_img,
                content = "أودع قاضي التحقيق بمحكمة الدار البيضاء، مساء الخميس، الجنرال المتقاعد علي غديري الحبس المؤقت بالحراش.\n" +
                        "وأكد بيان نشرته صفحة غديري، أنه “تم ايداع المترشح السابق للرئاسيات السيد علي غديري الحبس المؤقت، بعد مثوله أمام قاضي التحقيق لدى محكمة الدار البيضاء.\n" +
                        "وأوضح أنه “سيتم نشر بيان كاملا لاحقا” دون تقديم تفاصيل أكثر حول التهم التي يتابع فيها.\n" +
                        "\n" +
                        "\n" +
                        " \n" +
                        "وأكد صفحة غديري في بيان ثان أنه وجهت له تهمتان من قبل المحكمة هما “المشاركة في تسليم معلومات إلى عملاء دول أجنبية تمس بالاقتصاد الوطني”\n" +
                        "أما الثانية فهي “المساهمة في وقت السلم في مشروع لإضعاف الروح المعنوية للجيش قصد الإضرار بالدفاع الوطني” ونفت أن تكون محاكمته بسبب ما تم تداوله إعلاميا حول تزوير توكيلات الإنتخابات.",
                categorie = "POLITICS",
                published_at = Date(13, 6, 2019),
                author = "عبد الرزاق بوالقمح",
                url = "",
                suivi = false
            )

            private var article10: Article = Article(
                journal = echourouk,
                titre = "سريلانكا تسترد خمسة متورطين في تفجيرات عيد الفصح",
                urltoImage = R.drawable.article3_img,
                content = "علنت الشرطة السريلانكية، الجمعة، أنها استردت خمسة سريلانكيين مطلوبين لارتباطهم بتفجيرات الفصح الدامية بعد أن أوقفوا في دبي.\n" +
                        "\n" +
                        "\n" +
                        " \n" +
                        "وأوضحت الشرطة أن من بين المشتبه بهم العائدين، محمد ملهان وهو قائد بارز في جماعة التوحيد الوطنية التي اعتُبرت مسؤولة عن تفجيرات 21 أفريل التي أسفرت عن 258 قتيلاً من بينهم 45 أجنبياً.\n" +
                        "\n" +
                        "وقال المتحدث باسم الشرطة روان غوناسيكيرا في بيان إن “ضباطاً من قسم التحقيقات الجنائية أعادوا المشتبه بهم إلى سريلانكا هذا الصباح”. ولم تتوفر مزيد من التفاصيل على الفور.\n" +
                        "\n" +
                        "وهذه المرة الثانية التي يتمّ فيها توقيف مشتبه بهم خارج البلاد على ارتباط بالاعتداءات على ثلاث كنائس وثلاثة فنادق فخمة في البلاد تبناها تنظيم داعش.\n" +
                        "\n" +
                        "وأعلن قائد الجيش ماهيش سينانياكي الشهر الفائت، إن مشتبه بهما أوقفا في قطر والسعودية. ولم يكشف عن جنسيتيهما لكن مصادر رسمية قالت إنهما سريلانكيان.\n" +
                        "\n" +
                        "وأوقفت السلطات السريلانكية أكثر من مئة شخص على ارتباط بجماعة التوحيد الوطنية وزعيمها زهران هاشم الذي كان أحد الانتحاريين الاثنين اللذين استهدفا فندق “شانغري-لا” في كولومبو.\n" +
                        "\n" +
                        "وأعلنت سريلانكا حال الطوارئ منذ وقوع الاعتداءات التي أدت أيضاً إلى إصابة حوالى 500 شخص.\n" +
                        "\n" +
                        "وتواجه الشرطة وقوات الأمن اتهامات بالفشل في التصرف بعد تلقيها تحذيرات مسبقة من اعتداءات وشيكة.\n" +
                        "\n" +
                        "\n" +
                        " \n" +
                        "وأبلغ كبار ضباط الاستخبارات والشرطة، لجنة برلمانية تحقق في الفشل الأمني، بأنه كان من الممكن تجنّب الاعتداءات لو تحركت السلطات بناء على معلومات استخباراتية قدمتها الهند المجاورة.\n" +
                        "\n" +
                        "وفي الرابع من أفريل، قالت الهند للسلطات السريلانكية إن مشتبهاً به في الحبس الاحتياطي لديها كشف عن خطط مفصلة لتنفيذ هجوم دامٍ في سريلانكا يستهدف كنائس من بين أماكن أخرى.\n" +
                        "\n" +
                        "وأقال رئيس سريلانكا ماثريبالا سيريسينا رئيس الاستخبارات وضمن استقالة وزير الدولة لشؤون الدفاع وعلّق مهام قائد الشرطة.\n" +
                        "\n" +
                        "وردا على ذلك، قال هؤلاء إن سيريسينا تجاهل البروتوكولات الأمنية وينبغي أن يتحمل بنفسه المسؤولية بسبب الثغرات الرئيسية التي سمحت بتنفيذ العمليات الانتحارية.",
                categorie = R.string.international,
                published_at = Date(14, 6, 2019),
                author = "me in future",
                url = "",
                suivi = false
            )


        var listArticle : ArrayList<Article> = arrayListOf(
                article0,
                article1,
                article2,
                article3,
                article4,
                article5,
                article6,
                article7,
                article8,
                article9,
                article10
            )

            private var listArticleFavoris = ArrayList<Article>()

    /**
     *avoir le premier article
     *
     * web service
     */
    fun getFirstArticle():Article{
        return this.listArticle[0]
    }

    /**
     * avoir tous les autre article
     *
     * web service
     */
    fun getRestArticle():ArrayList<Article>{
        val arrayArticle = ArrayList<Article>()
        for( i in 1 until this.listArticle.size){
            arrayArticle.add(listArticle[i])
        }
        return arrayArticle
    }



    /**
     * construire l'article i
     */
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun construireArticle(articleUn: Article, image_news:ImageView
                          , logo:ImageView
                          , titre : TextView
                          , heurs : TextView
    ){
        val format = SimpleDateFormat("yyyy/MM/dd")
        val dateToday = articleUn.published_at
        val year = format.format(dateToday)
        image_news.setImageResource(articleUn.urltoImage!!)
        logo.setImageResource(articleUn.journal.logo!!)
        titre.text = articleUn.titre
        heurs.text = "a la une -$year"
    }


    /**
     * Avoir favoris
     */
    fun getAllFavoris() : ArrayList<Article>{
        val listfavoris = ArrayList<Article>()
        listfavoris.addAll(this.listArticleFavoris)
        return listfavoris
    }


    /**
     *
     */

    /**
     * list Article par categori
     */
    fun ListPerCategory(category : Int) : ArrayList<Article>{
        val listCategory = ArrayList<Article>()
        for(article in listArticle){
            if (article.categorie.equals(category)){
                listCategory.add(article)
            }
        }
        return listCategory
    }

}