import Athlete.AmateurAthlete;
import Athlete.ProAthlete;
import Athlete.StarAthlete;
import Club.Club;
import Club.Stadium;

import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) {
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

            //System.out.println("PRO" + proList.toString());

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

            //System.out.println("STAR" + starList.toString());

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


            Club club = new Club("River", new Stadium("Club", 500));

            List<Object> lista = new ArrayList<>();
            List<Club> clubs = new ArrayList<>();
            club.jsonToList(lista);

            for (var inst : lista){
                clubs.add((Club)inst);
            }

            System.out.println(clubs);

            //System.out.println("STADIUM" + stadiumList.toString());




        }
    }

