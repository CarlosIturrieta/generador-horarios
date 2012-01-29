

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author monkey
 */
public class Horario implements Comparable <Horario> {
    Clase[] clases;
    Dia[] dias;
    int totaldias;
    int peso;
    int horasmuertas;
    
    double media;
    double desviacion;
    
    Horario (Clase[] c){
        clases=c;
        totaldias=0;
        peso=0;
        horasmuertas=0;
        
        media=0; //sumatorio(dia[i].horafin-dia[i].horaini)/totaldias
        desviacion=0;
        
        dias = new Dia[7];
        for (int i=0; i<7;i++) dias[i]=new Dia();
        
        for (int i=0; i < clases.length; i++)
        {
            dias[clases[i].dia-1].Clase(c[i]);
        }
        
        for (int i=0; i<7;i++)
        {
            if (dias[i].horas != 0)
            {
                peso+=dias[i].tiempo;
                totaldias++;
                media+=dias[i].fin-dias[i].ini;
            }
            
            int init=Dia.hora(dias[i].ini),
                 fin=Dia.hora(dias[i].fin);
            
            for (int j=init; j <fin; j++){
                if (!dias[i].ocupado[j]){
                    dias[i].horasmuertas++;
                    horasmuertas++;
                }
            }
        }
        
        media/=totaldias;
        for (int i=0; i<7; i++){
            if (dias[i].horas!=0){
                desviacion += (dias[i].fin-dias[i].ini-media)*(dias[i].fin-dias[i].ini-media);
            }
        }
        desviacion/= (double)totaldias-1.;
        desviacion=Math.sqrt(desviacion);
        
    }

    @Override
    public int compareTo(Horario h) 
    { 
        int res = totaldias - h.totaldias;
        
        if (res == 0) res = horasmuertas - h.horasmuertas;
        
        if (res == 0) res = peso - h.peso;
        
        
        return res;
//        double res = desviacion - h.desviacion;
        
//        if      (res < 0) return -1;
//        else if (res > 0) return  1;
//        else {
//            
//            int r = horasmuertas - h.horasmuertas;
//            
//             if (r == 0) r = peso - h.peso;
//        
//             if (r == 0) r = totaldias - h.totaldias;
//        
//             return r;         
//        }
        
    }
    
}
