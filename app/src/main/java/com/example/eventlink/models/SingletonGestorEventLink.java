package com.example.eventlink.models;

import com.example.eventlink.R;

import java.util.ArrayList;

public class SingletonGestorEventLink {

    private static SingletonGestorEventLink instance = null;
    private ArrayList<Local> locais;
    private String ipServidor = "10.0.2.2";

    public static synchronized SingletonGestorEventLink getInstance() {
        if (instance == null) {
            instance = new SingletonGestorEventLink();
        }
        return instance;
    }

    private SingletonGestorEventLink() {
        gerarDadosTeste();
    }

    private void gerarDadosTeste() {
        locais = new ArrayList<>();

        locais.add(new Local(
                1,
                "Quinta do Vale Encantado",
                "Espaço amplo rodeado de jardins, ideal para casamentos e grandes eventos corporativos.",
                R.drawable.ic_launcher_background,
                "Estrada Nacional 1, Km 120",
                "Leiria",
                "2400-001",
                250,
                1200.00
        ));

        locais.add(new Local(
                2,
                "Auditório Oceano Atlântico",
                "Auditório moderno equipado com sistema de som e projeção 4K para conferências e workshops.",
                R.drawable.ic_launcher_background,
                "Avenida Marginal, nº 45",
                "Marinha Grande",
                "2430-120",
                120,
                650.00
        ));

        locais.add(new Local(
                3,
                "Pavilhão Industrial Loft",
                "Espaço urbano versátil para festas privadas, lançamentos de marca e exposições.",
                R.drawable.ic_launcher_background,
                "Zona Industrial Norte, Lote 8",
                "Leiria",
                "2415-300",
                400,
                1850.00
        ));

        locais.add(new Local(
                4,
                "Solar da Serra",
                "Casa senhorial histórica com salão nobre e terraço panorâmico.",
                R.drawable.ic_launcher_background,
                "Rua do Castelo, nº 12",
                "Porto de Mós",
                "2480-150",
                90,
                800.00
        ));
    }

    public ArrayList<Local> getLocais() {
        return new ArrayList<>(locais);
    }

    public ArrayList<String> getServicosExtraFicticios() {
        ArrayList<String> extras = new ArrayList<>();
        extras.add("Catering Completo");
        extras.add("Fotografia & Vídeo");
        extras.add("Animação / DJ");
        extras.add("Decoração Temática");
        return extras;
    }

    public Local getLocal(int id) {
        for (Local l : locais) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public void setIpServidor(String newIp) {
        if (newIp != null && !newIp.trim().isEmpty()) {
            this.ipServidor = newIp.trim();
        }
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public String getBaseUrlApi() {
        return "http://" + ipServidor + "/Projeto-web/backend/web/api/";
    }
}