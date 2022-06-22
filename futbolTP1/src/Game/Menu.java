package Game;

import Athlete.AmateurAthlete;
import Athlete.Athlete;
import Athlete.ProAthlete;
import Athlete.StarAthlete;
import Club.Club;
import Club.Stadium;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.*;

public class Menu {
    private Scanner scanner;
    private List<Club> clubs;
    private List<ProAthlete> proAthletes;
    private List<StarAthlete> starAthletes;
    private List<GamePlayer> players;
    private Tournament tournament;

    //region Constructor
    public Menu() {
        scanner = new Scanner(System.in);
        this.clubs = elements();
        this.proAthletes = jsonPro();
        this.starAthletes = jsonStar();
        this.players = new ArrayList<>();
        this.tournament = new Tournament(players);
    }

    //endregion
    //region Getters
    public List<Club> getClubs() {
        return clubs;
    }

    public List<ProAthlete> getProAthletes() {
        return proAthletes;
    }

    public List<StarAthlete> getStarAthletes() {
        return starAthletes;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public Tournament getTournament() {
        return tournament;
    }

    //endregion
    //region Setters
    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public void setProAthletes(List<ProAthlete> proAthletes) {
        this.proAthletes = proAthletes;
    }

    public void setStarAthletes(List<StarAthlete> starAthletes) {
        this.starAthletes = starAthletes;
    }

    public void setPlayers(List<GamePlayer> players) {
        this.players = players;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
//endregion
    //region Methods
            //Llama al menu de cada Usuario de un partido, quien arroja una respuesta en confirmacion de jugar o abandonar
            //si ambos confirman se realiza el partido, y sino, abandona el juego
    public boolean playOrExit() {
        boolean answer = true;
        getTournament().fixtureGenerate(getPlayers());
        for (var i : getTournament().getMatches()) {
            int result = playerMenu(i.getPlayer1());
            int result2 = playerMenu(i.getPlayer2());
            if (result != 0 && result2 != 0) {
                i.playMatch(getTournament().getStatistics(), clubs);
                getTournament().setCounter(tournament.getCounter() + 1);
            } else {
                System.out.println("See you soon");
                answer = false;
                List<Object> tournaments = new ArrayList<>();
                tournaments.add(tournament);
                tournament.save(tournaments, "ContinueGame.json");
                break;
            }
        }
        return answer;
    }

    public boolean continueGame() {
        boolean answer = true;
        int totalMatches = getTournament().getMatches().size();
        int index = tournament.getCounter();
        for ( int i = index ;  i < totalMatches ; i++) {
            int result = playerMenu(tournament.getMatches().get(i).getPlayer1());
            int result2 = playerMenu(tournament.getMatches().get(i).getPlayer2());
            System.out.println("result: " + result + "\nresult 2: "+ result2);
            if (result != 0 && result2 != 0) {
                tournament.getMatches().get(i).playMatch(getTournament().getStatistics(), clubs);
            } else {
                System.out.println("See you soon");
                answer = false;
                List<Object> tournaments = new ArrayList<>();
                tournaments.add(tournament);
                tournament.save(tournaments, "ContinueGame.json");
                break;
            }
        }
        return answer;
    }

            //Muestra estadisticas finales del torneo
    public void tournamentFinalStatistics() {
        System.out.println("-----------------------------------");
        System.out.println("\nPOSITIONS RANKING");
        getTournament().getStatistics().showPositions(getTournament().getStatistics().getPositions());
        System.out.println("\nSCORERS RANKING");
        getTournament().getStatistics().showPositions(getTournament().getStatistics().getScorers());
        System.out.println("\nHATTRICK RANKING");
        getTournament().getStatistics().showPositions(getTournament().getStatistics().getHattricks());
        System.out.println("\n");
    }
            //Una vez terminado el torneo, pregunta si quiere empezar uno nuevo
    public void newTournamentMenu() {
        int answer = -1;
        do {
            System.out.println("Do you want to continue with a new tournament??");
            System.out.println("0- No");
            System.out.println("1- Yes");
            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0:
                        break;
                    case 1:
                        playOrExit();
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e){
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Menu principal
    public void mainMenu() {
        int answer = -1;
        do {
            System.out.println("\n\nWelcome to UTNSoccer\n");
            System.out.println("1 - Start");
            System.out.println("2 - Continue Game");
            System.out.println("0 - Exit game");
            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0:
                        System.out.println("Thanks for play");
                        break;
                    case 1:
                        int result = generalPlayersCreator();
                        randomPlayerMenu(result);
                        boolean resp = playOrExit();
                        if (resp) {
                            tournamentFinalStatistics();
                            newTournamentMenu();
                        } else {
                            answer = 0;
                        }
                        break;
                    case 2:
                        List<Object> tournamentsContinue = new ArrayList<>();
                        tournament.jsonToList(tournamentsContinue, "ContinueGame.json");
                        List<Tournament> tournamentList = new ArrayList<>();
                        try{
                            for(var i : tournamentsContinue){
                                tournamentList.add((Tournament) i);
                            }
                            tournament = tournamentList.get(0);
                            boolean resp2 = continueGame();
                            if (resp2) {
                                tournamentFinalStatistics();
                                newTournamentMenu();
                            } else {
                                answer = 0;
                            }
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("You don't have any game to continue");
                        }
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e){
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Llama al generador de usuarios reales, luego llama al generador de usuarios Bot
    public int generalPlayersCreator() {
        int counter = 0;
        int answer = -1;
        System.out.println("CREATE NEW PLAYER");
        String name = realPlayerCreator();
        clubSelection(name);
        counter++;
        do {
            System.out.println("Create another new player??");
            System.out.println("1- Yes");
            System.out.println("0- NO");
            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0:
                        break;
                    case 1:
                        clubSelection(realPlayerCreator());
                        counter++;
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
        return counter;
    }
            //Pregunta la cantidad de participantes del torneo para generar los Bot restantes de los usuarios ya creados
    public void randomPlayerMenu(int counter) {
        int answer = 0;
        do {
            System.out.println("Number of participating clubs");
            System.out.println("4 - Clubs");
            System.out.println("8 - Clubs");
            try {
                answer = scanner.nextInt();
                int total = answer - counter;
                switch (answer) {
                    case 4:
                        randomPlayerCreator(total);
                        break;
                    case 8:
                        randomPlayerCreator(total);
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 4 && answer != 8);
    }
            //Generador de usuarios reales
    public String realPlayerCreator() {
        System.out.println("Insert your name");
        String name;
        scanner.nextLine();
        name = scanner.nextLine();
        boolean answer = nameValidator(name);
        while (answer) {
            System.out.println("Selected name already exists");
            System.out.println("Insert another name");
            name = scanner.nextLine();
            answer = nameValidator(name);
        }
        return name;
    }
            //El usuario selecciona un club de la lista de clubes
    public void clubSelection(String name){
        int clubOption;
        boolean flag = true;
        do {
            System.out.println("Select your club");
            showClubs();
           try {
               clubOption = scanner.nextInt();
               players.add(new GamePlayer(name, clubs.get(clubOption - 1)));
               clubs.remove(clubs.get(clubOption - 1));
               flag = true;
               } catch (InputMismatchException e) {
               System.out.println(Messages.INVALID.getMsg());
               flag = false;
            }catch (IndexOutOfBoundsException e){
               System.out.println(Messages.INVALID.getMsg());
               flag = false;
           }
            scanner.nextLine();
        }while (!flag);

    }
            //Genera usuarios Bot, cantidad por parametro
    public void randomPlayerCreator(int quantity) {
        for (int i = 0; i < quantity; i++) {
            players.add(new GamePlayer(clubs));
        }
    }
            //Menu q contiene todas las opciones de cada Usuario, antes de comenzar el partido
    public int playerMenu(GamePlayer player) {
        int answer = -1;
        if (player.getName().equalsIgnoreCase("PC")) {
            answer = 1;
        } else {
            do {
                System.out.println("-----------------------------------");
                System.out.println(Messages.TURN.getMsg().toUpperCase(Locale.ROOT) + player.getName().toUpperCase(Locale.ROOT));
                System.out.println("1- Play");
                System.out.println("2- Options");
                System.out.println("0- Exit");

                try {
                    answer = scanner.nextInt();

                    switch (answer) {
                        case 0:
                            break;
                        case 1:
                            //Toma el numero 1 para devolverlo
                            break;
                        case 2:
                            playerOptionsMenu(player);
                            break;
                        default:
                            System.out.println(Messages.INVALID.getMsg());
                    }
                }catch (InputMismatchException e) {
                    System.out.println(Messages.INVALID.getMsg());
                    scanner.nextLine();
                }
            } while (answer != 0 && answer != 1);
        }
        return answer;

    }
            //Venta de jugadores comprobando tamaño del equipo, y se asegura que, al vender un jugador
            // las finanzas sean suficientes para comprar otro, sino se invalida la venta
    public void sellingMenu(Club club) {
        int answer = -1;
        do {
            System.out.println(Messages.SELECT_ATHLETE.getMsg());
            club.checkTeam();
            System.out.println("0-"+Messages.EXIT.getMsg());
            try {
                answer = scanner.nextInt();
                if (answer != 0) {
                    int teamSize = club.getTeam().size();
                    int validator = (4 - teamSize) * 300;
                    if ((club.getFinances() + club.getTeam().get(answer - 1).getMarketValue() < validator)) {
                        System.out.println("You can´t sell this athlete");
                        System.out.println("If you sell this athlete, your finances are not enough to buy a Pro athlete");
                    } else {
                        switch (answer) {
                            case 1:
                                club.sellAthlete(club.getTeam().get(0));
                                break;
                            case 2:
                                club.sellAthlete(club.getTeam().get(1));
                                break;
                            case 3:
                                club.sellAthlete(club.getTeam().get(2));
                                break;
                            default:
                                System.out.println(Messages.INVALID.getMsg());
                        }
                    }
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }catch (IndexOutOfBoundsException e){
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        }
        while (answer != 0);
    }
            //Menu que permite comprar atleta, comprobando finanzas y tamaño del equipo
    public void buyingMenu(Club club) {
        int answer = -1;
        boolean validator = false;
        do {
            System.out.println("1-Pro");
            System.out.println("2-Star\n");
            System.out.println("0-"+Messages.EXIT.getMsg());
            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0:
                        break;
                    case 1:
                        Athlete pro = proAthleteList();
                        validator = club.buyAthlete(pro);
                        if (validator) {
                            getProAthletes().remove(pro);
                        }
                        break;
                    case 2:
                        Athlete star = starAthleteList();
                        validator = club.buyAthlete(star);
                        if (validator) {
                            getProAthletes().remove(star);
                        }
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }catch (IndexOutOfBoundsException e){
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Devuelve una lista cargada con archivo Json
    public List<ProAthlete> jsonPro() {
        ProAthlete pro = new ProAthlete();
        List<Object> list = new ArrayList<>();
        List<ProAthlete> proAthletes = new ArrayList<>();
        pro.jsonToList(list, "ProOriginal.json");

        for (var p : list) {
            proAthletes.add((ProAthlete) p);
        }
        return proAthletes;
    }
            //Muestra lista de atletas de la cual el usuario selecciona uno y se retorna
    public ProAthlete proAthleteList() {
        int resp;
        int index = 1;
        System.out.println(Messages.SELECT_ATHLETE.getMsg());
        for (var p : this.proAthletes) {
            System.out.println(index + "-" + ((ProAthlete) p).getName() + " " + ((ProAthlete) p).getLastName());
            index++;
        }
        resp = scanner.nextInt();
        ProAthlete athlete = getProAthletes().get(resp - 1);

        return athlete;
    }
            //Devuelve una lista cargada con archivo Json
    public List<StarAthlete> jsonStar() {
        StarAthlete star = new StarAthlete();
        List<Object> list = new ArrayList<>();
        List<StarAthlete> starAthletes = new ArrayList<>();
        star.jsonToList(list, "StarOriginal.json");
        for (var s : list) {
            starAthletes.add((StarAthlete) s);
        }
        return starAthletes;
    }
            //Muestra lista de atletas de la cual el usuario selecciona uno y se retorna
    public StarAthlete starAthleteList() {
        int answer;
        int index = 1;
        System.out.println(Messages.SELECT_ATHLETE.getMsg());
        for (var s : this.starAthletes) {
            System.out.println(index + "-" + ((StarAthlete) s).getName() + " " + ((StarAthlete) s).getLastName());
            index++;
        }
        answer = scanner.nextInt();
        StarAthlete athlete = getStarAthletes().get(answer - 1);

        return athlete;
    }
            //Muestra resultados de cada atleta seleccionado
    public void athleteMenu(Athlete athlete) {
        int answer = -1;
        do {

            System.out.println("-----------------------------------");
            System.out.println(athlete.getName().toUpperCase(Locale.ROOT) + " " + athlete.getLastName().toUpperCase());
            System.out.println("1- Goals and hattricks");
            System.out.println("2- Market value\n");
            System.out.println("0-" + Messages.EXIT.getMsg());

            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        if (athlete instanceof StarAthlete) {
                            System.out.println(athlete.getGoalCount() + " Goal(s) \n" + ((StarAthlete) athlete).getHattrickCount() + " HatTricks");
                        } else {
                            System.out.println(athlete.getGoalCount() + " Goal(s) \n");
                        }
                        break;
                    case 2:
                        System.out.println(athlete.getMarketValue() + " Futcoins");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Muestra lista de atletas para seleccionar
    public void checkAthletesMenu(Club club) {
        int answer = -1;
        do {

            System.out.println(Messages.SELECT_ATHLETE.getMsg());
            club.checkTeam();
            System.out.println("\n0-"+Messages.EXIT.getMsg());

            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0:
                        break;
                    case 1:
                        athleteMenu(club.getTeam().get(0));
                        break;
                    case 2:
                        athleteMenu(club.getTeam().get(1));
                        break;
                    case 3:
                        athleteMenu(club.getTeam().get(2));
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }catch (IndexOutOfBoundsException e){
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Muestra menu de operaciones del club (comprar - vender - mejorar estadio - ver finanzas)
    public void clubMenu(Club club) {
        int answer = -1;
        do {

            System.out.println("-----------------------------------");
            System.out.println("1- Check Finances");
            System.out.println("2- Buy an Athlete");
            System.out.println("3- Sell an Athlete");
            System.out.println("4- Stadium upgrade\n");
            System.out.println("0-"+Messages.EXIT.getMsg());

            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        System.out.println("The club has :" + club.getFinances() + " Futcoins");
                        break;
                    case 2:
                        buyingMenu(club);
                        break;
                    case 3:
                        sellingMenu(club);
                        break;
                    case 4:
                        club.raiseStadiumLevel();
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Muestra todos los atributos del estadio
    public void stadiumMenu(Stadium stadium) {
        int answer = -1;
        do {
            System.out.println("Stadium " + stadium.getName());

            System.out.println("-----------------------------------");
            System.out.println("1- Check capacity");
            System.out.println("2- Check level");
            System.out.println("3- Upgrade cost");
            System.out.println("4- Ticket value\n");
            System.out.println("0- "+Messages.EXIT.getMsg());

            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Capacity: " + stadium.getCapacity() + " seats");
                        break;
                    case 2:
                        System.out.println("Level: " + stadium.getLevel());
                        break;
                    case 3:
                        System.out.println("Upgrade cost: " + stadium.getUpgradeCost() + " Futcoins");
                        break;
                    case 4:
                        System.out.println("Ticket value: " + stadium.getTicketValue() + " Futcoins");
                        break;
                    default:
                        System.out.println(Messages.INVALID.getMsg());
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Permite acceder al equipo, estadio, estadisticas del torneo y a las opciones de club
    public void playerOptionsMenu(GamePlayer player) {
        int answer = -1;
        do {

            System.out.println("-----------------------------------");
            System.out.println("1- Check team");
            System.out.println("2- Check stadium");
            System.out.println("3- Check tournament statistics");
            System.out.println("4- Club options");
            System.out.println("0- "+Messages.EXIT.getMsg());

            try {
                answer = scanner.nextInt();
                if (answer == 0) {
                    while (player.getClub().getTeam().size() != 3) {
                        System.out.println("Your team size is not complete to keep playing");
                        clubMenu(player.getClub());
                    }
                }
                switch (answer) {
                    case 1:
                        checkAthletesMenu(player.getClub());
                        break;
                    case 2:
                        stadiumMenu(player.getClub().getStadium());
                        break;
                    case 3:
                        tournamentMenu(tournament.getStatistics());
                        break;
                    case 4:
                        clubMenu(player.getClub());
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Accede a estadisticas parciales, partidos jugados y por jugar
    public void tournamentMenu(Statistics statistics) {
        int answer = -1;
        do {

            System.out.println("-----------------------------------");
            System.out.println("1- Check clubs positions");
            System.out.println("2- Check scorers ranking");
            System.out.println("3- Check hattricks ranking");
            System.out.println("4- Played matches");
            System.out.println("5- Matches to play");
            System.out.println("0- "+Messages.EXIT.getMsg());

            try {
                answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        statistics.showPositions(statistics.getPositions());
                        break;
                    case 2:
                        statistics.showPositions(statistics.getScorers());
                        break;
                    case 3:
                        statistics.showPositions(statistics.getHattricks());
                        break;
                    case 4:
                        getTournament().matchesResults();
                        break;
                    case 5:
                        getTournament().nextMatches();
                }
            }catch (InputMismatchException e) {
                System.out.println(Messages.INVALID.getMsg());
                scanner.nextLine();
            }
        } while (answer != 0);
    }
            //Compurba que el nombre de usuario no sea repetido
    public boolean nameValidator(String name) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().toUpperCase(Locale.ROOT).equals(name.toUpperCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
            //Instanciacias y conformacion de listas
    public List<Club> elements() {

        var amateur1 = new AmateurAthlete("Nicolas", "de la Cruz", 23);
        var amateur2 = new AmateurAthlete("Brian", "Romero", 25);
        var amateur3 = new AmateurAthlete("Julian", "Alvarez", 24);
        var amateur4 = new AmateurAthlete("Sebastian", "Villa", 26);
        var amateur5 = new AmateurAthlete("Alan", "Varela", 25);
        var amateur6 = new AmateurAthlete("Dario", "Benedetto", 29);
        var amateur7 = new AmateurAthlete("David", "Sayago", 31);
        var amateur8 = new AmateurAthlete("Rodrigo", "Marquez", 25);
        var amateur9 = new AmateurAthlete("Leandro", "Benegas", 34);
        var amateur10 = new AmateurAthlete("Gabriel", "Hauche", 27);
        var amateur11 = new AmateurAthlete("Enzo", "Copetti", 21);
        var amateur12 = new AmateurAthlete("Marcelo", "Correa", 32);
        var amateur13 = new AmateurAthlete("Lucas", "Pratto", 25);
        var amateur14 = new AmateurAthlete("Luca", "Orellano", 28);
        var amateur15 = new AmateurAthlete("Sebastian", "Sosa", 24);
        var amateur16 = new AmateurAthlete("Eduardo", "Garcia", 21);
        var amateur17 = new AmateurAthlete("Cristian", "Barrios", 20);
        var amateur18 = new AmateurAthlete("Nicolas", "Blandi", 19);
        var amateur19 = new AmateurAthlete("Jesus", "Olmedo", 31);
        var amateur20 = new AmateurAthlete("Danilo", "Gomez", 22);
        var amateur21 = new AmateurAthlete("Lucas", "Beltran", 19);
        var amateur22 = new AmateurAthlete("Marco", "Ruben", 29);
        var amateur23 = new AmateurAthlete("Lucas", "Gamba", 30);
        var amateur24 = new AmateurAthlete("Milton", "Caraglio", 27);
        var amateur25 = new AmateurAthlete("Matias", "Godoy", 21);
        var amateur26 = new AmateurAthlete("Federico", "Girotti", 29);
        var amateur27 = new AmateurAthlete("Emerson", "Batalla", 33);
        var amateur28 = new AmateurAthlete("Mauro", "Boselli", 24);
        var amateur29 = new AmateurAthlete("Alan", "Marinelli", 30);
        var amateur30 = new AmateurAthlete("Blas", "Palavecino", 29);

        List amateurList = new ArrayList();

        amateurList.add(amateur1);
        amateurList.add(amateur2);
        amateurList.add(amateur3);
        amateurList.add(amateur4);
        amateurList.add(amateur5);
        amateurList.add(amateur6);
        amateurList.add(amateur7);
        amateurList.add(amateur8);
        amateurList.add(amateur9);
        amateurList.add(amateur10);
        amateurList.add(amateur11);
        amateurList.add(amateur12);
        amateurList.add(amateur13);
        amateurList.add(amateur14);
        amateurList.add(amateur15);
        amateurList.add(amateur16);
        amateurList.add(amateur17);
        amateurList.add(amateur18);
        amateurList.add(amateur19);
        amateurList.add(amateur20);
        amateurList.add(amateur21);
        amateurList.add(amateur22);
        amateurList.add(amateur23);
        amateurList.add(amateur24);
        amateurList.add(amateur25);
        amateurList.add(amateur26);
        amateurList.add(amateur27);
        amateurList.add(amateur28);
        amateurList.add(amateur29);
        amateurList.add(amateur30);

        //System.out.println("AMATEURS" + amateurList.toString());

        var pro1 = new ProAthlete("Rodrigo", "de Paul", 29);
        var pro2 = new ProAthlete("Angel", "Correa", 27);
        var pro3 = new ProAthlete("Papu", "Gomez", 25);
        var pro4 = new ProAthlete("Lucas", "Ocampos", 23);
        var pro5 = new ProAthlete("Franco", "Russo", 21);
        var pro6 = new ProAthlete("Marcos", "Acuña", 26);
        var pro7 = new ProAthlete("Emiliano", "Martinez", 22);
        var pro8 = new ProAthlete("Javier", "Pastore", 31);
        var pro9 = new ProAthlete("Angel", "Di Maria", 29);
        var pro10 = new ProAthlete("Gonzalo", "Montiel", 33);
        var pro11 = new ProAthlete("Paulo", "Dybala", 28);
        var pro12 = new ProAthlete("Nicolas", "Gonzalez", 24);

        List proList = new ArrayList();

        proList.add(pro1);
        proList.add(pro2);
        proList.add(pro3);
        proList.add(pro4);
        proList.add(pro5);
        proList.add(pro6);
        proList.add(pro7);
        proList.add(pro8);
        proList.add(pro9);
        proList.add(pro10);
        proList.add(pro11);
        proList.add(pro12);


        var star1 = new StarAthlete("Lionel", "Messi", 32);
        var star2 = new StarAthlete("Cristiano", "Ronaldo", 33);
        var star3 = new StarAthlete("Robert", "Lewandowski", 31);
        var star4 = new StarAthlete("Mohamed", "Salah", 29);
        var star5 = new StarAthlete("Kylian", "Mbappe", 27);
        var star6 = new StarAthlete("Erling", "Haaland", 32);
        var star7 = new StarAthlete("Karim", "Benzema", 31);
        var star8 = new StarAthlete("Luis", "Suarez", 33);
        var star9 = new StarAthlete("Romelu", "Lukaku", 30);
        var star10 = new StarAthlete("Paul", "Pogba", 26);
        var star11 = new StarAthlete("Diego", "Maradona", 60);
        var star12 = new StarAthlete("Claudio", "Caniggia", 50);

        List starList = new ArrayList();

        starList.add(star1);
        starList.add(star2);
        starList.add(star3);
        starList.add(star4);
        starList.add(star5);
        starList.add(star6);
        starList.add(star7);
        starList.add(star8);
        starList.add(star9);
        starList.add(star10);
        starList.add(star11);
        starList.add(star12);


        var stadiumRiver = new Stadium("Antonio Vespucio Liberti", 15000);
        var stadiumBoca = new Stadium("Alberto J Armando", 15000);
        var stadiumIndependiente = new Stadium("Libertadores de America", 15000);
        var stadiumRacing = new Stadium("Presidente Peron", 15000);
        var stadiumVelez = new Stadium("Jose Amalfitani", 15000);
        var stadiumSanLorenzo = new Stadium("Pedro Bidegain", 15000);
        var stadiumColon = new Stadium("Brigadier Gral Estanislao Lopez", 15000);
        var stadiumRosarioCentral = new Stadium("Gigante de arroyito", 15000);
        var stadiumTalleres = new Stadium("Mario Alberto Kempes", 15000);
        var stadiumEstudiantes = new Stadium("Uno Jorge Luis Hirschi", 15000);

        List stadiumList = new ArrayList();

        stadiumList.add(stadiumRiver);
        stadiumList.add(stadiumBoca);
        stadiumList.add(stadiumIndependiente);
        stadiumList.add(stadiumRacing);
        stadiumList.add(stadiumVelez);
        stadiumList.add(stadiumSanLorenzo);
        stadiumList.add(stadiumColon);
        stadiumList.add(stadiumRosarioCentral);
        stadiumList.add(stadiumTalleres);
        stadiumList.add(stadiumEstudiantes);


        Club river = new Club("River", stadiumRiver);
        river.getTeam().add(amateur1);
        river.getTeam().add(amateur2);
        river.getTeam().add(amateur3);

        Club boca = new Club("Boca", stadiumBoca);
        boca.getTeam().add(amateur4);
        boca.getTeam().add(amateur5);
        boca.getTeam().add(amateur6);

        Club independiente = new Club("Independiente", stadiumIndependiente);
        independiente.getTeam().add(amateur7);
        independiente.getTeam().add(amateur8);
        independiente.getTeam().add(amateur9);

        Club racing = new Club("Racing", stadiumRacing);
        racing.getTeam().add(amateur10);
        racing.getTeam().add(amateur11);
        racing.getTeam().add(amateur12);

        Club velez = new Club("Velez", stadiumVelez);
        velez.getTeam().add(amateur13);
        velez.getTeam().add(amateur14);
        velez.getTeam().add(amateur15);

        Club sanLorenzo = new Club("San Lorenzo", stadiumSanLorenzo);
        sanLorenzo.getTeam().add(amateur16);
        sanLorenzo.getTeam().add(amateur17);
        sanLorenzo.getTeam().add(amateur18);

        Club colon = new Club("Colon", stadiumColon);
        colon.getTeam().add(amateur19);
        colon.getTeam().add(amateur20);
        colon.getTeam().add(amateur21);

        Club rosario = new Club("Rosario Central", stadiumRosarioCentral);
        rosario.getTeam().add(amateur22);
        rosario.getTeam().add(amateur23);
        rosario.getTeam().add(amateur24);


        Club talleres = new Club("Talleres", stadiumTalleres);
        talleres.getTeam().add(amateur25);
        talleres.getTeam().add(amateur26);
        talleres.getTeam().add(amateur27);

        Club estudiantes = new Club("Estudiantes", stadiumEstudiantes);
        estudiantes.getTeam().add(amateur28);
        estudiantes.getTeam().add(amateur29);
        estudiantes.getTeam().add(amateur30);

        List<Object> clubList = new ArrayList<>();
        clubList.add(river);
        clubList.add(boca);
        clubList.add(independiente);
        clubList.add(racing);
        clubList.add(velez);
        clubList.add(sanLorenzo);
        clubList.add(colon);
        clubList.add(rosario);
        clubList.add(talleres);
        clubList.add(estudiantes);


        List<Object> lista = new ArrayList<>();
        List<Club> clubs1 = new ArrayList<>();
        river.jsonToList(lista, "ClubsOriginalOk.json");


        for (var inst : lista) {
            clubs1.add((Club) inst);
        }

        setClubs(clubs1);

        return clubs;
    }
            //Muestra lista de clubes
    public void showClubs() {
        List<String> clubs = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < this.clubs.size(); i++) {
            clubs.add(this.clubs.get(i).getName());
            System.out.println(index + " " + clubs.get(i));
            index++;
        }
    }
//endregion
}
