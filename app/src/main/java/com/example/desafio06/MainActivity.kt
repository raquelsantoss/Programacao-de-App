package com.example.desafio06

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Item(val titulo: String, val descricao: String)

class MainActivity : AppCompatActivity() {
    private val listaItens = listOf(
        Item("O Poderoso Chefão", "A saga de uma família ítalo-americana de Nova York, que se torna chefe da máfia durante a Segunda Guerra Mundial."),
        Item("A Lista de Schindler", "A história real de Oskar Schindler, um empresário alemão que salva a vida de mais de mil judeus durante o Holocausto."),
        Item("Forrest Gump", "A vida de Forrest Gump, um homem com baixo QI, mas uma vida cheia de acontecimentos notáveis."),
        Item("O Senhor dos Anéis: O Retorno do Rei", "A conclusão épica da trilogia, onde Frodo e Sam enfrentam a batalha final para destruir o Um Anel."),
        Item("O Silêncio dos Inocentes", "Uma jovem agente do FBI busca a ajuda de um serial killer para capturar outro assassino em série."),
        Item("Cidadão Kane", "A vida de Charles Foster Kane, magnata da mídia, contada através das memórias de pessoas que o conheciam."),
        Item("A Origem", "Um ladrão de sonhos é contratado para implantar uma ideia na mente de uma pessoa durante o sono."),
        Item("Gladiador", "Um ex-general romano busca vingança contra o corrupto imperador que o traiu e mandou para a morte."),
        Item("Pulp Fiction: Tempo de Violência", "Diferentes histórias se entrelaçam em uma trama envolvendo gangsters, boxeadores e um misterioso mala."),
        Item("A Vida é Bela", "Um pai tenta proteger seu filho de uma terrível realidade ao fazer com que ele acredite que estão em uma competição para ganhar um tanque de guerra."),
        Item("O Resgate do Soldado Ryan", "Um grupo de soldados é enviado atrás das linhas inimigas durante a Segunda Guerra Mundial para resgatar um soldado."),
    )

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val listViewItens = findViewById<ListView>(R.id.listViewItens)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaItens.map { it.titulo })
        listViewItens.adapter = adapter

        listViewItens.setOnItemClickListener { _, _, position, _ ->
            val itemSelecionado = listaItens[position]
            exibirDetalhes(itemSelecionado)
            salvarItemFavorito(itemSelecionado)
        }

        val ultimoItemFavorito = obterUltimoItemFavorito()
        if (ultimoItemFavorito != null) {
            exibirDetalhes(ultimoItemFavorito)
        }
    }

    private fun exibirDetalhes(item: Item) {
        val detalhesView = LayoutInflater.from(this).inflate(R.layout.layout_detalhes, null)
        detalhesView.findViewById<TextView>(R.id.textViewTitulo).text = item.titulo
        detalhesView.findViewById<TextView>(R.id.textViewDescricao).text = item.descricao

        val container = findViewById<LinearLayout>(R.id.containerDetalhes)
        container.removeAllViews()
        container.addView(detalhesView)
    }

    private fun salvarItemFavorito(item: Item) {
        val editor = sharedPreferences.edit()
        editor.putString("titulo", item.titulo)
        editor.putString("descricao", item.descricao)
        editor.apply()
    }

    private fun obterUltimoItemFavorito(): Item? {
        val titulo = sharedPreferences.getString("titulo", null)
        val descricao = sharedPreferences.getString("descricao", null)

        return if (titulo != null && descricao != null) {
            Item(titulo, descricao)
        } else {
            null
        }
    }
}
