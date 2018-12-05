package br.com.softplan.fatura.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.softplan.fatura.domain.NotaFiscal;

@Service
public class GeradorObservacaoService {
	
	private  String descricaoNota = "Fatura da%s nota%s %s de simples remessa: ";

	public String gerarObservacao(List<NotaFiscal> notasFiscais) {
		StringBuilder relatorio = new StringBuilder();
		if(notasFiscais != null && !notasFiscais.isEmpty()) {
			
			List<String> itensNota = notasFiscais.stream()
					.filter(n -> n.getCodigo() != null && n.getValor() != null)
					.map(this::gerarTextoItemNota).collect(Collectors.toList());
			
			if(itensNota.size() >= 2)
				relatorio.append( String.format(descricaoNota, "s", "s", "fiscais") );
			else
				relatorio.append( String.format(descricaoNota, "", "", "fiscal") );
			
			if(itensNota.size() == 2) {
				relatorio.append( String.join(" e ", itensNota) );
			} else if(itensNota.size() > 2) {
				relatorio.append( String.join(", ", itensNota.subList(0, itensNota.size() - 1)) + " e " +
						itensNota.get(itensNota.size() - 1)
				);
			} else {
				relatorio.append( itensNota.get(0) );
			}
			
			relatorio.append( ". Total = " + NumberFormat.getCurrencyInstance().format( 
					notasFiscais.stream()
						.filter(n -> n.getCodigo() != null && n.getValor() != null)
						.map(NotaFiscal::getValor).reduce(BigDecimal::add).get() ).replace("R$ ", "")
			);
		}
		return relatorio + ".";
	}
	
	private String gerarTextoItemNota(NotaFiscal notaFiscal) {
		return notaFiscal.getCodigo() + " cujo valor é " + NumberFormat.getCurrencyInstance().format( notaFiscal.getValor() );
	}
}
