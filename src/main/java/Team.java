public class Team {
        private JcoincheServer.JClient[]    players = new JcoincheServer.JClient[2];
        private int                         points;
        private int                         gamble;
        Team(JcoincheServer.JClient first, JcoincheServer.JClient second){
            players[0] = first;
            players[1] = second;
            points = 0;
            gamble = 0;
        }
}
