package little.old.me.ingestion.domain.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllianzV1 {

    @JsonProperty("DatiImmatricolazioni")
    private List<DatiImmatricolazioni> datiImmatricolazioni;

    @JsonProperty("DatiAttestati")
    private List<DatiAttestati> datiAttestati;

    @JsonProperty("DatiCoperture")
    private List<DatiCoperture> datiCoperture;

    @Data
    public static class DatiImmatricolazioni {
        @JsonProperty("Lunghezza")
        private String lunghezza;

        @JsonProperty("NumeroGiri")
        private String numeroGiri;

        @JsonProperty("Data")
        private String data;

        @JsonProperty("DataEmissione")
        private String dataEmissione;

        @JsonProperty("TipoEmissione")
        private String tipoEmissione;

        @JsonProperty("Causale")
        private String causale;

        @JsonProperty("Provincia")
        private String provincia;

        @JsonProperty("Descrizione")
        private String descrizione;

        @JsonProperty("CategoriaUso")
        private String categoriaUso;

        @JsonProperty("Tipo")
        private String tipo;

        @JsonProperty("Omologazione")
        private String omologazione;

        @JsonProperty("TargaTelaio")
        private String targaTelaio;

        @JsonProperty("Fabbricazione")
        private String fabbricazione;

        @JsonProperty("Carrozzeria")
        private String carrozzeria;

        @JsonProperty("ClasseInquinamento")
        private String classeInquinamento;

        @JsonProperty("CodiceFiscaleIntestatario")
        private String codiceFiscaleIntestatario;

        @JsonProperty("ProvinciaResidenza")
        private String provinciaResidenza;

        @JsonProperty("ComuneResidenza")
        private String comuneResidenza;

        @JsonProperty("ComuneIstatResidenza")
        private String comuneIstatResidenza;

        @JsonProperty("DataPossesso")
        private String dataPossesso;

        @JsonProperty("DataPrimaImmatricolazione")
        private String dataPrimaImmatricolazione;

        @JsonProperty("TargaPrecedente")
        private String targaPrecedente;

        @JsonProperty("Alimentazione")
        private String alimentazione;

        @JsonProperty("Cilindrata")
        private String cilindrata;

        @JsonProperty("NumeroCilindri")
        private String numeroCilindri;

        @JsonProperty("PotenzaFiscale")
        private String potenzaFiscale;

        @JsonProperty("PotenzaMassima")
        private String potenzaMassima;

        @JsonProperty("NumeroMarce")
        private String numeroMarce;

        @JsonProperty("VelocitaMax")
        private String velocitaMax;

        @JsonProperty("Larghezza")
        private String larghezza;

        @JsonProperty("NumeroPosti")
        private String numeroPosti;

        @JsonProperty("NumeroAssi")
        private String numeroAssi;

        @JsonProperty("Tara")
        private String tara;

        @JsonProperty("PesoPienoCarico")
        private String pesoPienoCarico;

        @JsonProperty("PesoMassimoRimorchiabile")
        private String pesoMassimoRimorchiabile;

        @JsonProperty("Telaio")
        private String telaio;
    }

    @Data
    public static class DatiAttestati {
        @JsonProperty("Impresa")
        private String impresa;

        @JsonProperty("Scadenza")
        private String scadenza;

        @JsonProperty("FormatoIdVeicolo")
        private String formatoIdVeicolo;

        @JsonProperty("TargaCompleta")
        private String targaCompleta;

        @JsonProperty("TipoVeicolo")
        private String tipoVeicolo;

        @JsonProperty("CodiceImpresa")
        private String codiceImpresa;

        @JsonProperty("ScadenzaContratto")
        private String scadenzaContratto;

        @JsonProperty("CUAssegnazione")
        private String cuAssegnazione;

        @JsonProperty("Format2015")
        private boolean format2015;

        @JsonProperty("ImpresaDecode")
        private String impresaDecode;

        @JsonProperty("ContraenteDescri")
        private String contraenteDescri;

        @JsonProperty("Contraente")
        private String contraente;

        @JsonProperty("TargaTelaio")
        private String targaTelaio;

        @JsonProperty("FranchigieNoncorrisposte")
        private String franchigieNoncorrisposte;

        @JsonProperty("ClassediProvenienza")
        private String classediProvenienza;

        @JsonProperty("ClassediProvenienzaCU")
        private String classediProvenienzaCU;

        @JsonProperty("AventeDiritto")
        private String aventeDiritto;

        @JsonProperty("FormaTariffaria")
        private String formaTariffaria;

        @JsonProperty("Importi")
        private String importi;

        @JsonProperty("ClassediAssegnazione")
        private String classediAssegnazione;

        @JsonProperty("ClassediAssegnazioneCU")
        private String classediAssegnazioneCU;

        @JsonProperty("CodiceLegge")
        private String codiceLegge;

        @JsonProperty("Polizza")
        private String polizza;

        @JsonProperty("CodiceFiscaleAventeDiritto")
        private String codiceFiscaleAventeDiritto;

        @JsonProperty("CodiceIUR")
        private String codiceIUR;

        @JsonProperty("TipoPolizza")
        private String tipoPolizza;

        @JsonProperty("AnniAttestato")
        private List<AnniAttestato> anniAttestato;
    }

    @Data
    public static class AnniAttestato {
        @JsonProperty("Ultimoanno")
        private String ultimoanno;

        @JsonProperty("NumeroSinistriPrincipali")
        private String numeroSinistriPrincipali;

        @JsonProperty("NumeroSinistriCosePrincipale")
        private String numeroSinistriCosePrincipale;

        @JsonProperty("NumeroSinistriPersonePrincipale")
        private String numeroSinistriPersonePrincipale;

        @JsonProperty("NumeroSinistriMistiPrincipale")
        private String numeroSinistriMistiPrincipale;

        @JsonProperty("NumeroSinistriParitari")
        private String numeroSinistriParitari;

        @JsonProperty("NumeroSinistriCoseParitari")
        private String numeroSinistriCoseParitari;

        @JsonProperty("NumeroSinistriPersoneParitari")
        private String numeroSinistriPersoneParitari;

        @JsonProperty("NumeroSinistriMistiParitari")
        private String numeroSinistriMistiParitari;

        @JsonProperty("NumeroSinistriPagati")
        private String numeroSinistriPagati;

        @JsonProperty("NumeroSinistriPersone")
        private String numeroSinistriPersone;

        @JsonProperty("NumeroSinistriCose")
        private String numeroSinistriCose;
    }

    @Data
    public static class DatiCoperture {
        @JsonProperty("ImpresaDecode")
        private String impresaDecode;

        @JsonProperty("Impresa")
        private String impresa;

        @JsonProperty("Contraente")
        private String contraente;

        @JsonProperty("Movimento")
        private String movimento;

        @JsonProperty("CausaleSmall")
        private String causaleSmall;

        @JsonProperty("CodiceIUR")
        private String codiceIUR;

        @JsonProperty("DataEffetto")
        private String dataEffetto;

        @JsonProperty("ScandenzaCopertura")
        private String scandenzaCopertura;

        @JsonProperty("ScadenzaContratto")
        private String scadenzaContratto;

        @JsonProperty("TacitoRinnovo")
        private String tacitoRinnovo;

        @JsonProperty("Polizza")
        private String polizza;

        @JsonProperty("Tariffa")
        private String tariffa;

        @JsonProperty("GiorniEstensionecopertura")
        private String giorniEstensionecopertura;

        @JsonProperty("BlackBox")
        private String blackBox;

        @JsonProperty("TipoPolizza")
        private String tipoPolizza;

        @JsonProperty("DataIncasso")
        private String dataIncasso;

        @JsonProperty("TipoVeicoloMCTC")
        private String tipoVeicoloMCTC;
    }
}
