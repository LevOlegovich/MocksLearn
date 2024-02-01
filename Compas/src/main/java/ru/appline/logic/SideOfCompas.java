package ru.appline.logic;


    public class SideOfCompas {
        private String side;
        private int startDegree;
        private int endDegree;


        public SideOfCompas(String side, int startDegree, int endDegree) {
            this.side = side;
            this.startDegree = startDegree;
            this.endDegree = endDegree;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public int getStartDegree() {
            return startDegree;
        }

        public void setStartDegree(int startDegree) {
            this.startDegree = startDegree;
        }

        public int getEndDegree() {
            return endDegree;
        }

        public void setEndDegree(int endDegree) {
            this.endDegree = endDegree;
        }
    }

