# A comparison of Machine Learning Algorithms

### 1. Naive Bayes

#### 1.a: simple 

``` 
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/simple-test.txt
     Estimator: MaximumLikelihoodEstimation with Normalization count=4
     Class 0: tested 2, correctly classified 2
     Class 1: tested 2, correctly classified 2
     Overall: tested 4, correctly classified 4
     Accuracy = 1.0

DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/simple-test.txt
     Estimator: LaplasceEstimation with Normalization count=12
     Class 0: tested 2, correctly classified 2
     Class 1: tested 2, correctly classified 2
     Overall: tested 4, correctly classified 4
     Accuracy = 1.0
``` 

#### 1.b: vote 

``` 
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/vote-test.txt
     Estimator: MaximumLikelihoodEstimation with Normalization count=300
     Class 0: tested 52, correctly classified 39
     Class 1: tested 83, correctly classified 83
     Overall: tested 135, correctly classified 122
     Accuracy = 0.9037037037037037

DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/vote-test.txt
     Estimator: LaplasceEstimation with Normalization count=492
     Class 0: tested 52, correctly classified 39
     Class 1: tested 83, correctly classified 83
     Overall: tested 135, correctly classified 122
     Accuracy = 0.9037037037037037
``` 

#### 1.c heart 

``` 
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
     Estimator: MaximumLikelihoodEstimation with Normalization count=80
     Class 0: tested 15, correctly classified 10
     Class 1: tested 172, correctly classified 135
     Overall: tested 187, correctly classified 145
     Accuracy = 0.7754010695187166

DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
     Estimator: LaplasceEstimation with Normalization count=168
     Class 0: tested 15, correctly classified 10
     Class 1: tested 172, correctly classified 130
     Overall: tested 187, correctly classified 140
     Accuracy = 0.7486631016042781
```  
          
#### 1.d q&a


##### After running your algorithms on both the vote and heart data, did you see any difference between using Maximum Likelihood Estimators versus Laplace Estimators in your accuracy results?

Laplasce performed as well as Maximum Likelihood for vote, but performed worse for heart. 

##### In general, under what conditions (i.e., characteristics of the data sets) do you think using Laplace Estimators (rather than Maximum Likelihood Estimators) would be better (e.g., likely improve classification accuracy)?

Laplasce does better for datasets where it is reasonable that probability be non-zero for any tuple input/output random variable value.  This may be particularly useful for small datasets of sparse data, where introducing a minumum count of one (bias) is a better assumption than zero probability.  For example it would be risky to assume a plane has zero probability of crash when we first start gathering data on a new model.

##### Under what conditions do you think using Maximum Likelihood Estimators (rather than Laplace Estimators) would be better?

Ultimately Maximum Likelihood performs better in overall accuracy for larger datasets, as no bias is introduced--as exemplified with our heart dataset above.  


### 2. Logistic Reggression

#### 2.a: simple 

```
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/simple-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/simple-train.txt
	GIVEN_LEARNING_RATE=1.0E-4 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[0.7986507346344086, -0.08890108038158384]
	INPUT_VAR_GRADIENT=[0.6399929402683063, -0.14812802293223992]
	Class 0: tested 2, correctly classified 2
	Class 1: tested 2, correctly classified 2
	Overall: tested 4, correctly classified 4
	Accuracy = 1.0
``` 

#### 2.b: vote

``` 
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/vote-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/vote-train.txt
	GIVEN_LEARNING_RATE=1.0E-4 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[0.3088186442468318, -0.020498682781842262, -0.09246647978965576, 0.011298662683619292, 0.6724556240894888, -0.48790080509777195, -0.8132107805341494, 1.1294311599180755, -0.12036689770859642, 3.279791593407976, -2.8514996993620785, -0.23243841237056553, 0.4272371962037264, -0.4581842393968592, 0.22680052486846378, -0.1739678197143195, 0.28730242157947006, 0.08251887981018396, 0.6442367848586678, -0.45500338799494394, 0.006620084811608794, 0.715934775723535, 0.1444085326604535, -0.6644898267086576, -0.6436008988596377, 1.001216982558299, -0.16176260202332707, 0.7869559104594358, -0.582208524440605, -0.008893904343498104, -1.414584523934264, 1.2921080685564907, 0.3183299370530949, 1.2012121431282785, -0.2744920143954481, -0.7308666470575, 0.0773151333488145, -0.18902578691880087, 0.30756413524532134, 0.3260693520826653, 0.006318261365473381, -0.1365341317728087, -0.3290368122383291, 0.7737541157639778, -0.2488638218503203, 0.37898405492567, -0.6481897439618816, 0.46505917071154895]
	INPUT_VAR_GRADIENT=[0.22805430590095613, -0.1556170115725064, -0.05115622796165614, 0.06514326231811102, 0.28538947482990773, -0.32925167078122425, -0.22441358508054018, 0.3325986790580634, -0.0869040276107303, 1.0518901206428712, -0.8221633427748001, -0.20844571150127927, -0.0762888336907852, -0.09842275684076418, 0.1959926568983415, -0.057711124654769214, -0.04554504576446039, 0.12453723678602285, 0.2000073612851706, -0.28032331664231, 0.10159702172393226, 0.4228208162085464, -0.042107946934438895, -0.3594318029073156, -0.22499502809888106, 0.5565216418560989, -0.3102455473904256, 0.26262628853384273, -0.35147511464895176, 0.11012989248190169, -0.4852057912265888, 0.0995730303910749, 0.40691382720230745, 0.5132449098231138, 0.11474740168186029, -0.606711245138181, -0.23369169322423242, -0.20282601290534316, 0.4577987724963691, -0.256334760007925, 0.2580078147176965, 0.019608011657021127, 0.017266883282427148, 0.12066518603019712, -0.11665100294583075, 0.24773227103937917, -0.33033818239708285, 0.10388697772449633]
	Class 0: tested 52, correctly classified 51
	Class 1: tested 83, correctly classified 83
	Overall: tested 135, correctly classified 134
	Accuracy = 0.9925925925925926
```  

##### Were you able to obtain a higher classification accuracy than with the two (parameter estimation) variants of the Naïve Bayes classifier on this data? Briefly explain why or why not this was the case

With Naive Bayes, for vote per above, we obtained accuracy of 0.90--for both Maximum Likelihood and Laplasce estimators. 

For vote, Logistic regression clearly "generalized" better, with nearly perfect 0.99 accuracy for unseen data

Both Naive Bayes Classification and Logistic Regression attempt to linearly divide the data.

The main difference is that they maximize different objective functions:
* Naive Bayes: P(X|Y), creating a "generative model", which assumes that Xi are independent of each other
* Logistic Regression: P(Y|X), creating a "discriminative model", making no assumption on the independence of Xi

Per the articles below, Logistic Regression performs better because it has lower asymnptotic error[1][2].  Gausian Bayes models also approach their asymptotic error much faster O(n), while Logistic Multinomial models doe it slower O(log n).  

Sources: 
[1] Sanghamitra Deb, "Naive Bayes vs Logistic Regression" https://medium.com/@sangha_deb/naive-bayes-vs-logistic-regression-a319b07a5d4c
[2] Andrew Ng and Michael I Jordan, "On Discriminative vs Generative Classifiers" http://ai.stanford.edu/~ang/papers/nips01-discriminativegenerative.pdf


#### 2.c heart 

``` 
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-train.txt
	GIVEN_LEARNING_RATE=1.0E-4 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[-0.39797225873691244, -0.15664861614405612, -1.077276964197092, 0.18324110750996755, -0.4863504762481397, 0.02731479378399925, 0.27810753474838673, 0.9230228147820323, -0.20014831939723296, 0.3407789130216812, 0.8055894628793033, -0.13393327555598436, 1.068879248313341, 0.010926817282957206, 0.2077186848586589, 1.0357269097195358, 1.1507085579507623, 0.5050490108193212, -0.0873973807010242, 0.062025136603650555, 0.16935060597073, 0.08338775220113079]
	INPUT_VAR_GRADIENT=[-0.2260637841159981, -0.31704885424892554, -1.0039236614176066, 0.07930690988868005, -0.15634847213521913, -0.09416696744252717, 0.12419879512801024, 0.5104647617094037, -0.21750064568106753, 0.43593877409239135, 0.25567079942382587, -0.24416131190633072, 0.4077782961038422, 0.05441465217447439, 0.08300109205891448, 0.5700811785375057, 0.7994154037094339, 0.2924632313978611, -0.11644035004381992, -0.13705030536362106, 0.015942667392632837, -0.08055526643202326]
	Class 0: tested 15, correctly classified 12
	Class 1: tested 172, correctly classified 147
	Overall: tested 187, correctly classified 159
	Accuracy = 0.8502673796791443
``` 

##### Were you able to obtain a higher classification accuracy than with the two (parameter estimation) variants of the Naïve Bayes classifier on this data? Briefly explain why or why not this was the case.     
     
For heart, Logistic Regression attained an accuracy of 0.85, while Naive bayes only: a) 0.78 with Maximum Likelihood estimator, and b) 0.75 with Laplasce estimator.

As per vote, Logistic Regression makes no assumption on the independence of input variables, and has lower asymptotic error, which is it outperforms Naive Bayes estimators     


#### 2.d heart experiment


``` 
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-train.txt
	GIVEN_LEARNING_RATE=0.001 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[-1.9251456450138078, -1.1498970815596572, -4.581597612015812, 0.7435447373586329, -0.2361418533222492, -0.05142470125239567, 0.8851388259869971, 3.4393694577079246, -1.7346052394441043, 1.833737232002481, 0.9166424925578344, -0.8616594921251736, 1.8814381969382976, 1.0761419069254725, 0.5407666098404721, 2.4427199526674968, 4.278042994268411, 1.7346839008841966, -0.4196431357857655, -0.2535013602566681, 0.2773277376943043, -0.043781215883124704]
	INPUT_VAR_GRADIENT=[-0.11969159586974307, -0.028109098621971418, -0.19680696413473903, 0.034875888453532344, 0.048083179208370935, -0.006738444292683998, 0.019410872318968708, 0.17135396779980272, -0.1273456617775376, 0.0832062513505199, -0.005053058847449887, -0.023330941868396515, 0.019649961480670497, 0.10152413564068502, 0.03183885790828478, 0.03850791100571782, 0.170799392868192, 0.07721653472410883, -0.016317399813210953, -0.004815041654403718, 0.010047939963878927, -0.001373300002572131]
	Class 0: tested 15, correctly classified 11
	Class 1: tested 172, correctly classified 123
	Overall: tested 187, correctly classified 134
	Accuracy = 0.7165775401069518
	
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-train.txt
	GIVEN_LEARNING_RATE=2.0E-4 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[-0.6191515259226055, -0.428613741297482, -1.8958607296261794, 0.27231834036179015, -0.5730090636416024, -0.03737470236606043, 0.4078086056376422, 1.3802273720930656, -0.4045423921178323, 0.7059133984917986, 0.9425939132486144, -0.3308712534513578, 1.3519876693229258, 0.11194070757191886, 0.27119310090415055, 1.4769301099953251, 1.8330806223824556, 0.7537973471275264, -0.17839333258177728, -0.0548007287049696, 0.18569480323855403, 0.032604275598833055]
	INPUT_VAR_GRADIENT=[-0.2167699179475862, -0.22927735188876774, -0.6734540217254135, 0.09064555218396864, -0.03634377769546698, -0.035962321527028385, 0.12527063467677146, 0.41503833476083973, -0.20153302522838912, 0.30126921063527173, 0.0640462673711516, -0.16091987611675834, 0.20126789462872485, 0.12555821541494216, 0.04966280775064302, 0.3426708218289396, 0.5884346868055196, 0.2155868333110702, -0.06906345504749495, -0.09206632284516014, 0.012910214431724465, -0.03238609788334601]
	Class 0: tested 15, correctly classified 12
	Class 1: tested 172, correctly classified 140
	Overall: tested 187, correctly classified 152
	Accuracy = 0.8128342245989305
	
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-train.txt
	GIVEN_LEARNING_RATE=4.0E-5 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[-0.24353044036508525, 0.03817636129293205, -0.3779470632699241, 0.15296004973628932, -0.3341236614628034, 0.07628917385380209, 0.21550573594938396, 0.5730672128740298, -0.04322214485989653, 0.07001855594347611, 0.5419725333182663, 0.03781882179885349, 0.7231904178416305, 0.026611989183815982, 0.1438998846331095, 0.6131055085328153, 0.5977376817016985, 0.29832024438299454, -0.014421680480760372, 0.13155741192246462, 0.17107278742995463, 0.152915096094294]
	INPUT_VAR_GRADIENT=[-0.35815508693001535, -0.29278472360921537, -1.340860617368746, 0.00906465791651967, -0.43365583961122706, -0.024751643992075856, 0.09018300492156406, 0.7103521392807548, -0.3351761882782027, 0.3965093780146623, 0.7289656929584525, -0.32115383304452483, 0.8294956952166527, -0.1447049290552488, 0.14314460292648612, 0.8926486340887182, 1.0955190264678087, 0.42618284222774283, -0.11384769733886752, -0.05335252311362482, -0.0309662909449947, -0.14820343799805413]
	Class 0: tested 15, correctly classified 9
	Class 1: tested 172, correctly classified 157
	Overall: tested 187, correctly classified 166
	Accuracy = 0.8877005347593583
	
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-train.txt
	GIVEN_LEARNING_RATE=8.000000000000001E-6 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[-0.01804013026409993, 0.08702430603797988, 0.04490419445382702, 0.12759865812502486, -0.0648850672473257, 0.05449549536962607, 0.15408357711934034, 0.25704839161022247, 0.06736366546858116, 0.03760964873025155, 0.20213732182952063, 0.11442193751812924, 0.3294802841531089, 0.08560503369117896, 0.07142779271817688, 0.2376298391815021, 0.18271194075360728, 0.11676957679546553, 0.03318244102523956, 0.10814695377131357, 0.1515367205253434, 0.161630103652724]
	INPUT_VAR_GRADIENT=[-1.2412831000955236, 0.21179696100577983, -0.894426170739709, 0.48258293503873473, -1.5009811980894163, 0.22586351563442042, 0.6176757030014666, 1.7241042416478742, -0.12058342425289215, -0.36079329940822735, 1.6065817890423197, 0.14627417237608853, 2.20364732945039, 0.023387752209389157, 0.4538600784509452, 1.8087958805100757, 1.6534319882227493, 0.8843179970991154, -0.20570232559122326, 0.40153336715096244, 0.53601319919065, 0.5168132371836061]
	Class 0: tested 15, correctly classified 7
	Class 1: tested 172, correctly classified 165
	Overall: tested 187, correctly classified 172
	Accuracy = 0.9197860962566845
	
DATASET_ACCURACY=/Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-test.txt
	Estimator: LogisticEstimation /Users/pablo/git/JunoMoneta/Learn/Mehran/data/heart-train.txt
	GIVEN_LEARNING_RATE=1.6000000000000004E-6 GIVEN_NUM_EPOCHS=10000
	INPUT_VAR_BETAS=[0.030727065307417284, 0.04074815250637388, 0.047386133900178366, 0.054114076963318394, 0.012532995068793112, 0.023566355895720963, 0.0655985998711665, 0.08887514994708083, 0.0392865979111575, 0.0355228782518767, 0.06449096870752044, 0.05745921730134597, 0.1141588134415942, 0.04547619720845208, 0.02524488390509066, 0.07718413192335757, 0.05316628383367619, 0.038308077162588744, 0.026078910265633387, 0.04725673389462708, 0.06563997215104406, 0.07136492953992928]
	INPUT_VAR_GRADIENT=[0.7223920033220464, 1.8072702826504916, 1.7608632631684675, 2.5041577514827877, -0.13339989381834383, 1.0652875718412453, 3.008525291819397, 4.416941671864282, 1.641967696665416, 1.2478759840097795, 3.276650839781398, 2.4962676052596167, 5.667355921037887, 1.9431530030275064, 1.2456099324604182, 3.9055442178404984, 2.797219507732501, 1.9231945807056565, 0.9674017483366085, 2.141250025195286, 3.0082791948686824, 3.253792082439119]
	Class 0: tested 15, correctly classified 7
	Class 1: tested 172, correctly classified 168
	Overall: tested 187, correctly classified 175
	Accuracy = 0.9358288770053476											
``` 


##### Report the highest classification accuracy you could obtain on the testing data, and what was the value of the learning rate η you used to obtain it.

Accuracy increased steadily by reducing learning rate, until learning rate 1.6E-6 (0.0000016).  At which point accuracy reached 0.936.

##### Explain why do you think the learning rate had such an effect on your classification accuracy (e.g., specific characteristics of the data sets, characteristics of the learning algorithm, etc.)?

Per the project source, learning rate is a direct factor on changes on beta estimations, which are used to establish probability of a datapoint.

``` 
    private void updateBeta() {
        for(int j=0; j<getNumInputColumns(); j++) {
            double currentBeta = inputVarBetas.get(j);
            double currentGradient = inputVarGradient.get(j);
            double deltaBeta = learningRate*currentGradient;
            double newBeta = currentBeta+deltaBeta;
            inputVarBetas.set(j, newBeta);
        }
        // System.out.println("INPUT_VAR_BETA="+inputVarBetas);
    }
   ``` 
    
By using a relatively large number of epochs, the algorithm had a chance to overfit.    


