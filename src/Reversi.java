import java.util.Scanner;
public class Reversi {                                         			// Debut du programme pour le jeu: Reversi, Othelo
    static Scanner in = new Scanner (System.in);
    static Scanner sc = new Scanner (System.in);

    public static void main (String  [] args){
        int l, c;														// l = ligne et c = colonne
        int v;															// v = l'entrée permettant soit de rejouer, soit d'arreter le programme
        int i, k;														// i et k deux variables d'entier permettant de parcourir le tableau
        int n,b;                                                        // Pour la couleur des pions n = Pion noir ; b = Pion Blanc
        int jn,jb;                                                      // jn = Parties jouées par le joueur noir ; jb = Parties jouées par le joueur blanc
        String premierJoueur, deuxiemeJoueur;
        char [] [] Tab;                                                 // Tableau à deux dimensions correspondant au plateau de jeu
        char [] lettres={'A','B','C','D','E','F','G','H'} ;             // Tableau à une dimension affichant les lettres correspondant aux colonnes
        v=0;															// Initialisation du v
        n=0;
        b=0;
        jn=0;
        jb=0;
        while (v==0){                                                   //Pour avoir le choix entre rejouer ou arreter le programme apres que la partie soit terminé
            c=8;
            l=8;
            Tab = new char [c][l];                                      //Création du tableau à deux dimensions avec comme nombre de colonnes 8 et le nombre de lignes 8
            System.out.print("Le joueur ayant choisis la couleur noir, veillez entrer votre nom: ");
            premierJoueur=in.next();                                  							    //Pour lire le nom du joueur noir
            System.out.print("Le joueur ayant choisis la couleur blanche, veillez entrer votre nom: ");
            deuxiemeJoueur=in.next();														        //Pour lire le nom du joueur blanc
            initialiser(Tab);																		//Pour executer le sous-programme "initialiser"
            partie (Tab, lettres, premierJoueur, deuxiemeJoueur);									//Pour executer le sous-programme "partie"
            for (i=0; i<Tab[0].length; i++){
                for (k=0; k<Tab.length; k++){
                    if (Tab[k][i]=='N' ){
                        n++;
                    }else{
                        if (Tab[k][i]=='B')
                            b++;
                    }
                }
            }
            System.out.println("Le nombre de pions noirs est: " + n);								//Permettant d'afficher le score du joueur noir
            System.out.println("Le nombre de pions blancs est: " + b);								//Permettant d'afficher le score du joueur blanc
            if (n < b){
                System.out.println("Bravo !! " + deuxiemeJoueur + " remporte cette partie.");
                jb++;
            }else{
                if (n > b){
                    System.out.println("Bravo !! " + premierJoueur + " remporte cette partie.");
                    jn++;
                }else{
                    System.out.println("Aucun de vous ne remporte cette partie, match nul.");
                }
            }
            System.out.println("Le nombre de parties remportees par " + premierJoueur + " est "+ jn);		//Pour l'affichage du score du joueur avec des pions noirs
            System.out.println("Le nombre de parties remportees par "+ deuxiemeJoueur + " est "+ jb);		//Pour l'affichage du score du joueur avec des pions blancs
            jn=0;
            jb=0;
            System.out.print("Si vous voulez rejouer, ou prendre votre revanche, tappez 0; Si vous voulez vous arretez la, tappez 1: ");
            v = in.nextInt();
        }
    }




    //Sous-Programme "initialiser" permettant d'initialiser les 4 pions de départ des deux joueurs

    public static void initialiser (char [][] Tab) {
        for (int i=0; i< Tab.length; i++) {
            for (int j=0; j< Tab.length; j++) {
                if ( (i==3 && j==3) || (i==3 && j==4)) {								//Pour poser les deux premiers pions noirs aux cases D4 et D5
                    Tab [i][j]='N';
                }else{
                    if ((i==4 && j==3) || (i==4 && j==4)) {								//Pour poser les deux premiers pions blancs auc cases E4 et E5
                        Tab [i][j]='B';
                    }else{
                        Tab [i][j]= ' ';												//Pour placer les cases vides dans les autres cases
                    }
                }
            }
        }
    }



    //Sous-Programme "affichage" permettant d'afficher le tableau ainsi que les lignes et les colonnes

    public static void affichage (char [][] Tab, char [] lettres){
        for (int i=0; i<Tab.length; i++){
            System.out.print("  ");
            for (int j=0; j<Tab.length; j++){
                System.out.print(" ");
            }
            System.out.println();
            System.out.print((8-i) + " | ");									// (8-i) Permettant l'affichage des numeros de haut en bas allant de 8 à 1 correspondant aux lignes
            for (int u=0; u<Tab.length; u++){
                System.out.print(Tab[u][i] + "_ | ");
            }
            System.out.println();												//Pour retourner a la ligne apres chaque 8 cases du tableau
        }
        System.out.print("  ");
        System.out.println();
        for ( int k=0; k<lettres.length; k++){
            System.out.print("    "+lettres[k]);								// Pour afficher les lettres correspondant aux colonnes allant de A à H
        }
        System.out.println(" ");												// Pour retourner a la ligne apres avoir afficher le tableau au complet
    }





    //Sous-Programme "couleurPionOppose" permettant de retourner "la couleur du pion opposée"

    public static char couleurPionOppose (char joueur){
        char cpo;														//cpo = la couleur du pion opposé
        if (joueur=='N'){												//Si le premier joueur est de couleur noire, la couleur du pion opposé serra blanche
            cpo ='B';
        }else{															//Si le deuxieme joueur est de couleur blanche, la couleur du pion opposé serra noire
            cpo ='N';
        }
        return cpo;
    }




    //Sous-Programme "caseValide" permettant de retouner une case valide
    //

    public static boolean caseValide(int c,int l){
        boolean caseValide=false;
        c=-1;
        l=-1;
        if(c < 0 || l < 0){
            return caseValide;
        }
        int b = 0;
        int n = 0;

        if (c==b || c==n){
            return false;
        }
        return caseValide;
    }




    /* A partir de ce point la nous utiliserons les quatres point cardinaux pour se reperer (Nord-Est-Sud-Ouest)*/

    //On note que pour chaque direction on a deux cas exemple: pour une diagonale allant de Sud-Ouest à Nord-Est on a deux coté a prendre en consideration le Gauche ainsi que le Droit



    //Sous-Programme "testEstOuest" permettant de tester si le mouvement est possible sur la direction "Est-Ouest" (horizontalement) et si c'est le cas compter le nombre de pions a changer

    public static void testEstOuest (int l, int c, char [][] Tab, char joueur ){
        int i,k,j;																	// la variable k permettant de compter le nombres de pions à changer sur l'horizontal
        boolean d;
        d=true;																		// initialisation du boolean d
        k=0;																		// initialisation de la variable k
        for ( i=1; (c+i)<Tab.length && d; i++){										// Cette premiere boucle for c'est pour faire les tests sur la direction "Ouest"
            if (Tab[c+i][l]== couleurPionOppose(joueur)){							// apres avoir entrer la position du pion on verifie les couleurs des pions a l'Ouest de celle-ci
                k++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c+i][l]==joueur && k!=0){									//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c+j][l]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
        d=true;
        k=0;
        for ( i=1; (c-i)>=0 && d; i++){												// Cette deuxieme boucle for c'est pour faire les tests sur la direction "Est"(droite)
            if (Tab[c-i][l]== couleurPionOppose(joueur)){							// apres avoir entrer la position du pion on verifie les couleurs des pions a l'Est de celui-ci
                k++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c-i][l]==joueur && k!=0){									//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c-j][l]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
    }




    //Sous-Programme "testNordSud" permettant de tester si le mouvement est possible sur la direction "Nord-Sud" (verticalement) et si c'est le cas compter le nombre de pions a changer

    public static void testNordSud (int l, int c, char[][] Tab, char joueur ){
        int i,k,j;                                                                      // la variable k permettant de compter le nombres de pions à changer sur la verticale
        boolean d;
        d= true;																		// initialisation du boolean d
        k=0;																			// initialisation de la variable k
        for (i=1; (l+i)<Tab[0].length && d;i++){										// Cette premiere boucle for c'est pour faire les tests sur la direction "Nord"(haut)
            if (Tab[c][l+i]== couleurPionOppose(joueur)){								// Apres avoir entrer la position du pion on verifie les couleurs des pions au Nord de celle-ci
                k++;																	// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c][l+i]==joueur && k!=0){									//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c][l+j]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
        d=true;
        k=0;
        for (i=1; (l-i)>=0; i++){														// Cette deuxieme boucle for c'est pour faire les tests sur la direction "Sud"(bas)
            if (Tab[c][l-i]== couleurPionOppose(joueur)){								// Apres avoir entrer la position du pion on verifie les couleurs des pions au Sud de celle-ci
                k++;																	// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c][l-i]==joueur && k!=0){										//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c][l-j]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
    }




    //Sous-Programme "diagonaleNordOuestSudEst" permettant de tester si le mouvement est possible sur la direction "diagonale allant du cote Nord-Ouest ainsi que celle allant du cote Sud-Est" et si c'est le cas compter le nombre de pions a changer

    public static void diagonaleNordOuestSudEst (int l, int c, char[][] Tab, char joueur ){
        int io,iv,k,j;																			// la variable k permettant de compter le nombres de pions à changer sur la diagonale
        boolean d;
        d=true;																					// initialisation du boolean d
        k=0;																					// initialisation de la variable k
        for (io=1 , iv=1; ((c+io)<Tab.length && (l-iv)>=0) && d; io++, iv++ ){					// Cette premiere boucle for c'est pour faire les tests sur la diagonale "Sud-Est"(droite)
            if (Tab[c+io][l-iv]== couleurPionOppose(joueur)){									// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la diagonale "Sud-Est" de celle-ci
                k++;																			// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c+io][l-iv]==joueur && k!=0){											//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c+j][l-j]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
        d=true;
        k=0;
        for (io=1 , iv=1; (c-io)>=0 && (l+iv)<Tab[0].length; io++, iv++ ){						// Cette deuxieme boucle for c'est pour faire les tests sur la diagonale "Nord-Ouest"(gauche)
            if (Tab[c-io][l+iv]== couleurPionOppose(joueur)){									// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la diagonale "Nord-Ouest" de celle-ci
                k++;																			// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c-io][l+iv]==joueur && k!=0){											//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c-j][l+j]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
    }




    //Sous-Programme "diagonaleSudOuestNordEst" permettant de tester si le mouvement est possible sur la direction "diagonale allant du cote Sud-Ouest ainsi que celle allant du cote Nord-Est" et si c'est le cas compter le nombre de pions a changer

    public static void diagonaleSudOuestNordEst (int l, int c, char[][] Tab, char joueur ){
        int io,iv,k,j;																			// la variable k permettant de compter le nombres de pions à changer sur la diagonale
        boolean d;
        d= true;																				// initialisation du boolean d
        k=0;																					// initialisation de la variable k
        for (io=1 , iv=1; ((c-io)>=0 && (l-iv)>=0) && d; io++, iv++ ){							// Cette premiere boucle for c'est pour faire les tests sur la diagonale "Sud-Ouest"(gauche)
            if (Tab[c-io][l-iv]== couleurPionOppose(joueur)){									// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la diagonale "Sud-Ouest" de celle-ci
                k++;																			// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c-io][l-iv]==joueur && k!=0){											//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c-j][l-j]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
        d=true;
        k=0;
        for (io=1 , iv=1; ((c+io)<Tab.length && (l+iv)<Tab[0].length) && d; io++, iv++ ){		// Cette deuxieme boucle for c'est pour faire les tests sur la diagonale "Nord-Est"(droite)
            if (Tab[c+io][l+iv]== couleurPionOppose(joueur)){									// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la diagonale "Nord-Est" de celle-ci
                k++;																			// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c+io][l+iv]==joueur && k!=0){											//Pour remplacer les pions de couleurs opposée sur cette direction par ceux du joueur ayant placer le pion
                    for (j=0; j<=k; j++){
                        Tab[c+j][l+j]=joueur;
                    }
                }else{
                    d=false;
                }
            }
        }
    }




    //Sous-Programme "placerPionEstOuest"(horizontal) permettant de placer un pion horizontalement aprés avoir effectuer le "testEstOuest"

    public static boolean placerPionEstOuest (int l, int c, char[][] Tab, char joueur ){
        int i,u;																	//la variable u permettant de compter le nombre de pions a changer
        boolean b1, b2, b3;															//b1-b2-b3 sont des boolean permettant de controler si les mouvements sont possibles ou pas
        boolean d;																	// d boolean qui permet de controler les boucles
        d=true;																		//initialisation du boolean d
        b1=false;																	//initialisation du boolean b1 en false
        b2=false;																	//initialisation du boolean b2 en false
        u=0;																		//initialisation de la variable u
        for (i=1; (c+i)<Tab.length && d;i++){	                                    // Boucle permettant de parcourir la direction "Est" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c+i][l]== couleurPionOppose(joueur)){							// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Est" de celle-ci
                u++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable u la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c+i][l]==joueur && u!=0){
                    b1=true;														//b1 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        d=true;
        u=0;																		//initialisation de la variable u
        for (i=1; (c-i)>=0 && d ;i++){												// Boucle permettant de parcourir la direction "Ouest" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c-i][l]== couleurPionOppose(joueur)){							// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Ouest" de celle-ci
                u++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable u la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c-i][l]==joueur && u!=0){
                    b2=true;														//b2 = true si le mouvement est accepté (une possibilté de placement)
                }else{
                    d=false;
                }
            }
        }
        b3=b1||b2;																	//On affecte au boolean b3 l'expression (b1 ou b2) cette affectation va permettre de controler à gauche et à droite du pion joué
        if (Tab[c][l]!= ' '){														//Si toutes les cases mis a part celle entrée par le joueur sont vides on affecte a b3 = false
            b3=false;
        }
        return b3;																	//On retourne b3
    }




    //Sous-Programme "placerPionNordSud"(horizontal) permettant de placer un pion verticalement aprés avoir effectuer le "testNordSud"

    public static boolean placerPionNordSud (int l, int c, char[][] Tab, char joueur ){
        int i,u;																	//la variable u permettant de compter le nombre de pions a changer
        boolean b1, b2, b3;															//b1-b2-b3 sont des boolean permettant de controler si les mouvements sont possibles ou pas
        boolean d;																	// d boolean qui permet de controler les boucles
        d= true;																	//initialisation du boolean d
        b1=false;																	//initialisation du boolean b1 en false
        b2=false;																	//initialisation du boolean b2 en false
        u=0;																		//initialisation de la variable u
        for(i=1; (l+i)<Tab[0].length && d; i++){									// Boucle permettant de parcourir la direction "Sud" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c][l+i]== couleurPionOppose(joueur)){							// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Sud" de celle-ci
                u++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable u la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c][l+i]==joueur && u!=0){
                    b1=true;														//b1 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        d= true;
        u=0;																		//initialisation de la variable u
        for (i=1; (l-i)>=0 && d; i++){												// Boucle permettant de parcourir la direction "Nord" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c][l-i]== couleurPionOppose(joueur)){								// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Nord" de celle-ci
                u++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable u la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c][l-i]==joueur && u!=0){
                    b2=true;														//b2 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        b3=b1||b2;																	//On affecte au boolean b3 l'expression (b1 ou b2) cette affectation va permettre de controler à gauche et à droite du pion joué
        if (Tab[c][l]!= ' '){														//Si toutes les cases mis a part celle entrée par le joueur sont vides on affecte a b3 = false
            b3=false;
        }
        return b3;																	//On retourne b3
    }




    //Sous-Programme "placerPionNordOuestSudEst"(diagonale) permettant de placer un pion verticalement aprés avoir effectuer le "diagonaleNordOuestSudEst"

    public static boolean placerPionNordOuestSudEst (int l, int c, char[][] Tab, char joueur ){
        int io,iv,k;																//la variable k permettant de compter le nombre de pions a changer
        boolean b1, b2, b3;															//b1-b2-b3 sont des boolean permettant de controler si les mouvements sont possibles ou pas
        boolean d;																	// d boolean qui permet de controler les boucles
        d=true;																		//initialisation du boolean d
        b1=false;																	//initialisation du boolean b1 en false
        b2=false;																	//initialisation du boolean b2 en false
        k=0;																		//initialisation de la variable k
        for (io=1 , iv=1; ((c+io)<Tab.length && (l-iv)>=0) && d; io++, iv++ ){		// Boucle permettant de parcourir la direction "Sud-Est" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c+io][l-iv]== couleurPionOppose(joueur)){						// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Sud-Est" de celle-ci
                k++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c+io][l-iv]==joueur && k!=0){
                    b1=true;														//b1 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        d=true;
        k=0;																		//initialisation de la variable k
        for (io=1 , iv=1; ((c-io)>=0 && (l+iv)<Tab[0].length) && d; io++, iv++ ){	// Boucle permettant de parcourir la direction "Nord-Ouest" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c-io][l+iv]== couleurPionOppose(joueur)){						// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Nord-Ouest" de celle-ci
                k++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c-io][l+iv]==joueur && k!=0){
                    b2=true;														//b2 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        b3=b1||b2;																	//On affecte au boolean b3 l'expression (b1 ou b2) cette affectation va permettre de controler à gauche et à droite du pion joué
        if (Tab[c][l]!= ' '){														//Si toutes les cases mis a part celle entrée par le joueur sont vides on affecte a b3 = false
            b3=false;
        }
        return b3;																	//On retourne b3
    }




    //Sous-Programme "placerPionSudOuestNordEst"(horizontal) permettant de placer un pion verticalement aprés avoir effectuer le "diagonaleSudOuestNordEst"

    public static boolean placerPionSudOuestNordEst (int l, int c, char[][] Tab, char joueur ){
        int io,iv,k;																//la variable k permettant de compter le nombre de pions a changer
        boolean b1, b2, b3;															//b1-b2-b3 sont des boolean permettant de controler si les mouvements sont possibles ou pas
        boolean d;																	// d boolean qui permet de controler les boucles
        d=true;																		//initialisation du boolean d
        b1=false;																	//initialisation du boolean b1 en false
        b2=false;																	//initialisation du boolean b2 en false
        k=0;																		//initialisation de la variable k
        for (io=1 , iv=1; ((c-io)>=0 && (l-iv)>=0) && d; io++, iv++ ){				// Boucle permettant de parcourir la direction "Sud-Ouest" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c-io][l-iv]== couleurPionOppose(joueur)){						// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Sud-Ouest" de celle-ci
                k++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c-io][l-iv]==joueur && k!=0){
                    b1=true;														//b1 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        d= true;
        k=0;																		//initialisation de la variable k
        for (io=1 , iv=1; ((c+io)<Tab.length && (l+iv)<Tab[0].length) && d; io++, iv++ ){	// Boucle permettant de parcourir la direction "Nord-Est" a partir du pion joué et ainsi pouvoir compter le nombre de pions a changer
            if (Tab[c+io][l+iv]== couleurPionOppose(joueur)){						// Apres avoir entrer la position du pion on verifie les couleurs des pions sur la direction "Sud-Est" de celle-ci
                k++;																// Si cette couleur est differente a celle du joueur on ajoute 1 à la variable k la boucle se répete autant de fois qu'il le faudra
            }else{
                if (Tab[c+io][c+iv]==joueur && k!=0){
                    b2=true;														//b2 = true si le mouvement est accepté (une possibilité de placement)
                }else{
                    d=false;
                }
            }
        }
        b3=b1||b2;																	//On affecte au boolean b3 l'expression (b1 ou b2) cette affectation va permettre de controler à gauche et à droite du pion joué
        if (Tab[c][l]!= ' '){														//Si toutes les cases mis a part celle entrée par le joueur sont vides on affecte a b3 = false
            b3=false;
        }
        return b3;																	//On retourne b3
    }




    //Sous-Programme "partie" permettant de jouer une partie avec affichage du vainqueur avec son score

    public static void partie (char [][] Tab, char [] lettres, String premierJoueur, String deuxiemeJoueur){
        int li;																		// correspond à la ligne initiale
        char ci;																	// correspond à la colonne initiale
        int l, c;																	//lignes et colonnes (réelles)
        boolean v;																	//variable booleenne permettant de distinguer les deux joueurs , afin de faire changer les rôles
        boolean pf;																	//pf = partie finie
        boolean mv1,mv2, mv;		           										//-mv1 c'est pour verifier la colonne -mv2 c'est pour verifier la ligne -mv c'est pour verifier si la case contient deja un pion
        boolean b1, b2, b3, b4, b;													//b1-b2-b3-b4 sont des boolean permettant de controler si les mouvements sont possibles ou pas
        boolean d;
        boolean dm;																	//dm sert a arreter la partie si on peut pas jouer dm = false si on continue dm = true
        int i,k;
        char joueur;																//charactère du joueur en cour soit n soit b
        int [] lignes = {8,7,6,5,4,3,2,1};
        l=0;
        c=0;
        v = false;
        pf = false;																	//initialisation de pf
        mv= false;
        affichage(Tab,lettres);
        while (pf==false){															// permet de savoir si la partie est terminée et qu'il y a plus de mouvement pour les noirs et les blancs
            v=!v;
            if (v){
                joueur='N';
            }else{
                joueur='B';
            }
            dm=true;
            b=false;
            d=false;
            for (i=0; i<Tab[0].length; i++){
                for (k=0; k<Tab.length; k++){
                    b1 = placerPionEstOuest(i,k,Tab,joueur);
                    b2 = placerPionNordSud(i,k,Tab,joueur);
                    b3 = placerPionNordOuestSudEst(i,k,Tab,joueur);					// permettent de placer les pions sur une colonnes ou ligne vide
                    b4 = placerPionSudOuestNordEst(i,k,Tab,joueur);
                    if ((b1 || b2) || (b3 || b4)){
                        b=true;
                    }

                }
            }
            if (d == false && b == false){                						   // permet de verifier si un joueur ne peut plus jouer afin d'arrêter la partie
                System.out.println("Impossible de continuer,la partie est terminee! ");
                pf=true;
                dm=false;
            }else{
                if (b== false){
                    if (joueur=='N'){
                        System.out.println( premierJoueur + " ,desole vous n'avez plus de coups possible");
                        dm=false;
                    }else{
                        System.out.println("Il n'y a plus de coup disponible pour "+ deuxiemeJoueur);
                        dm=false;
                    }
                }
            }
            mv1 = false;
            mv2 = false;
            if (dm){
                mv=false;
                if (v){
                    System.out.println("C'est a " +premierJoueur+ " (noir) de jouer");	// indique que c'est au joueur noir de jouer
                    joueur='N';
                }else{
                    System.out.println("C'est a "+ deuxiemeJoueur+" (blanc) de jouer");		// indique que c'est au joueur blanc de jouer
                    joueur='B';
                }
            }else{
                mv=true;
            }
            while (mv==false){                       								//permet de verifier si la ligne et la colonne saisis sont valides sinon on va redemander d'entrée une nouvelle saisie
                while(mv1==false){ 													//Boucle concernant la verification colonne
                    System.out.print("Veillez saisir une colonne: ");
                    ci= (in.next()).charAt(0);										//Pour lire le charactère
                    for (i=0 ; i<lettres.length; i++){        					//Permet de faire correspondre les lignes afin de les inserer dans le tableau
                        if (ci==lettres[i]){
                            c=i;
                            mv1=true;
                        }
                    }
                    if (mv1==false)												//Dans le cas ou la colonne n'est pas valide ou le mv1 boolean est false
                        System.out.println("La colonne saisie n'est pas valide! ");
                }
                mv1=false;
                while(mv2==false){													//Boucle concernant la verification ligne
                    System.out.print("Veillez saisir une ligne: ");
                    li=in.nextInt();												//Pour lire la ligne
                    for (i=0; i< lignes.length ; i++){                   			//Permet de faire correspondre les lignes afin de les inserer dans le tableau.
                        if (li == lignes[i]){
                            l=i;
                            mv2=true;
                        }

                    }
                    if (mv2==false)													//Dans le cas ou la ligne n'est pas valide ou le mv2 boolean est false
                        System.out.println("La ligne saisie n'est pas valide! ");
                }
                mv2=false;
                mv=true;
                if(Tab[c][l]!= ' '){												//Dans le cas ou la case est deja occupée
                    System.out.println("Erreur,la case est déjà occuppée! ");
                    mv=false;
                }
                if (((placerPionEstOuest(l,c,Tab,joueur)||placerPionNordSud(l,c,Tab,joueur))||(placerPionNordOuestSudEst(l,c,Tab,joueur)||placerPionSudOuestNordEst(l,c,Tab,joueur)))==false && mv==true){
                    mv=false;
                    System.out.println("Erreur ,c'est pas valide! ");
                }// permet de savoir si les lignes et les colonnes existent afin d'entrer dans le tableau
            }
            if (dm){															//permet de controler si les mouvements sont disponibles
                testEstOuest(l,c,Tab,joueur);														//horizontale
                testNordSud(l,c,Tab,joueur);														//verticale
                diagonaleNordOuestSudEst(l,c,Tab,joueur);											//diagonale Nord Ouest (gauche) et Sud Est (droite)
                diagonaleSudOuestNordEst(l,c,Tab,joueur);											//diagonale Sud Ouest (gauche) et Nord Est (droite)
                affichage(Tab, lettres);															//affichage aprés mise a jour (que le joueur est entrée une case valide)
            }
        }
    }
}