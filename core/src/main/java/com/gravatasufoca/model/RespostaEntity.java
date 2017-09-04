package com.gravatasufoca.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Map;

/**
 * criado por bruno em 03/09/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RespostaEntity implements Serializable {

    private static final long serialVersionUID = 4313161695002722794L;

    private Map<String,String> mensagens;
    private Object resultado;


    private RespostaEntity() {
    }

    @XmlElementWrapper(name = "mensagens")
    @XmlElement(name = "mensagem")
    public Map<String, String> getMensagens() {
        return mensagens;
    }

    @XmlAttribute(name = "resultado")
    public Object getResultado() {
        return resultado;
    }


    public static class RespostaEntityBuilder{
        private RespostaEntity respostaEntity=new RespostaEntity();

        public RespostaEntity build(){
            return respostaEntity;
        }

        public RespostaEntityBuilder setMensagens(Map<String, String> mensagens){
            respostaEntity.mensagens=mensagens;
            return this;
        }

        public RespostaEntityBuilder setObjeto(Object objeto){
            respostaEntity.resultado=objeto;
            return this;
        }
    }

}
