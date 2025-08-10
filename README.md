# Epic Mini — Batalha 3×3 (Java + POO)

Um mini-jogo inspirado em *Epic Seven* para treinar os pilares de Programação Orientada a Objetos (POO) em Java:
**abstração, herança, polimorfismo e encapsulamento**.  
Times 3×3 são gerados aleatoriamente (classe + estrelas + nome) e batalham em turnos alternados.

> **Mensagem pessoal**  
> Eu curto muito jogos e estou começando agora na programação — descobrir que posso criar coisas incríveis por conta própria tem sido demais!  
> Este projeto foi maravilhoso de fazer e quero melhorá-lo sempre, adicionando novas funcionalidades e aprendendo no processo. 

---

## ✨ Funcionalidades (atual)

- **Times 3×3 aleatórios** com classe e estrelas (3★–5★).  
  _Onde:_ `Team.randomTeam(...)`, `HeroFactory.createRandomHero()`

- **Estrelas influenciam atributos** (tabelas por classe/estrela).  
  _Onde:_ `HeroFactory` — tabelas `WARRIOR_STATS`, `MAGE_STATS`, `ARCHER_STATS`

- **Nomes autênticos do Epic Seven** por classe.  
  _Onde:_ `HeroFactory` — arrays `WARRIOR_NAMES`, `MAGE_NAMES`, `ARCHER_NAMES`

- **Batalha 3×3 em turnos alternados entre times**  
  (um atacante vivo por turno, alvo vivo aleatório).  
  _Onde:_ `TeamBattle.fight(Team, Team)`

- **Cálculo de dano por classe** (comportamentos diferentes):
    - Warrior → dano estável (+3)
    - Mage → 25% crítico ×1.5
    - Archer → variação leve (0..3)  
      _Onde:_ `Warrior.attackDamage()`, `Mage.attackDamage()`, `Archer.attackDamage()`

- **Encapsulamento do estado de combate** (HP atual, aplicar defesa, reset).  
  _Onde:_ `Hero.takeDamage(int)`, `Hero.isAlive()`, `Hero.reset()`, `Hero.getCurrentHp()`

- **Resumo do vencedor no final da luta**.  
  _Onde:_ retorno de `TeamBattle.fight(...)` (tipo `Team`) e impressão na `Main`

---

## 🧩 4 Pilares de POO — Onde aparece no código

### 1) Abstração
- **Ideia:** definir o “molde” do que é um herói, sem detalhar o ataque.
- **Onde:** `Hero` (classe **abstrata**)
    - Atributos comuns: `name`, `clazz`, `stars`, `attack`, `defense`, `hp`, `currentHp`
    - **Contrato**: `public abstract int attackDamage();`

### 2) Herança
- **Ideia:** especializar o herói genérico em classes concretas.
- **Onde:** `Warrior`, `Mage`, `Archer` **estendem** `Hero`
    - Herdam os atributos/comportamentos básicos e só definem o que muda.

### 3) Polimorfismo (sobrescrita)
- **Ideia:** mesmo método, comportamentos diferentes conforme a classe.
- **Onde:** `attackDamage()` **sobrescrito** em:
    - `Warrior.attackDamage()` → `getAttack() + 3`
    - `Mage.attackDamage()` → crítico 25% ×1.5
    - `Archer.attackDamage()` → `getAttack() + variação(0..3)`
- **Uso prático:** em `TeamBattle.fight(...)`, o código chama **sempre** `attacker.attackDamage()` sem saber a classe concreta (o Java resolve em tempo de execução).

### 4) Encapsulamento
- **Ideia:** proteger estado e regras dentro da própria classe.
- **Onde:** `Hero`
    - Campos **privados** (`private`) com **getters** de leitura.
    - **Tomar dano** com defesa aplicada internamente: `takeDamage(int raw)`
    - **Controle de vida/estado:** `isAlive()`, `reset()`, `getCurrentHp()`
    - Regras de segurança: dano efetivo **mínimo 1**, HP **nunca negativo**.

---

## 🗺️ Arquitetura em 10s (fluxo)

`Main` cria times → `Team.randomTeam` pede heróis à `HeroFactory` (classe, estrelas, nome, stats) →  
`TeamBattle.fight` alterna turnos e usa `attackDamage()` + `takeDamage()` → imprime logs e retorna o **time vencedor**.


---

## 🧱 Estrutura (resumo)

```
src/
└─ main/
├─ java/
│ └─ jogoEpic/
│ ├─ Main.java # ponto de entrada (3×3)
│ ├─ Hero.java # classe base (abstrata)
│ ├─ Warrior.java # herói: dano estável
│ ├─ Mage.java # herói: crítico 25% x1.5
│ ├─ Archer.java # herói: variação leve
│ ├─ HeroFactory.java # nomes, estrelas e stats por classe
│ ├─ Team.java # equipe 3×3
│ └─ TeamBattle.java # mecânica de combate 3×3
└─ resources/
```

---

## ▶️ Como rodar

### IntelliJ IDEA
1. Abra o projeto (Maven).
2. Rode a classe `jogoEpic.Main`.

### Maven (linha de comando)
Caso use o plugin `exec`, você pode rodar com:
```bash

mvn -q exec:java -Dexec.mainClass=jogoEpic.Main
```

Se ainda não tem o plugin, adicione ao pom.xml (dentro de <build><plugins>):

```bash
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>3.1.0</version>
  <configuration>
    <mainClass>jogoEpic.Main</mainClass>
  </configuration>
</plugin>
```
## 🔎 Exemplo de saída (3×3)

```text
=== EPIC MINI 3x3 ===

=== BATALHA 3x3 ===
Time Azul [Vivian (Mage ★★★★★) ATK:27 DEF:5 HP:105/105, Nakwol (Archer ★★★) ATK:18 DEF:4 HP:92/92, Lua (Archer ★★★) ATK:18 DEF:4 HP:92/92]
Time Vermelho [Cerise (Archer ★★★★★) ATK:24 DEF:6 HP:115/115, Araminta (Mage ★★★★) ATK:24 DEF:4 HP:95/95, Aria (Mage ★★★) ATK:20 DEF:3 HP:85/85]
Quem começa: Time Azul
Round 1: [Time Azul] Vivian → Aria [Time Vermelho] (dano 24, HP alvo 61)
Round 2: [Time Vermelho] Araminta → Lua [Time Azul] (dano 32, HP alvo 60)
Round 3: [Time Azul] Lua → Cerise [Time Vermelho] (dano 13, HP alvo 102)
...
Round 29: [Time Azul] Vivian → Cerise [Time Vermelho] (dano 21, HP alvo 0)
Round 30: [Time Vermelho] Aria → Vivian [Time Azul] (dano 15, HP alvo 0)
Round 31: [Time Azul] Nakwol → Aria [Time Vermelho] (dano 18, HP alvo 28)
Round 32: [Time Vermelho] Aria → Nakwol [Time Azul] (dano 16, HP alvo 0)
Time vencedor: Time Vermelho (sobreviventes: 1 x 0)
```


## 🛣️ Próximos passos (ideias)
- Itens (espada, poção) e sobrecarga (attackDamage(Item)).

- Alvos “inteligentes” (focar menor HP, maior ATK/DEF).

- Melhor de 3 entre times, placar e MVP por dano.

- Habilidades únicas por classe (stun, burn, bleed…).

- Probabilidade ajustável de estrelas (5★ mais raras).

- Adição de mais classes (Suporte, tank)

- Melhoria no sistema de "gacha" de heróis

- Melhoria no retorno de informações como hp restante e andamento da batalha

- Habilidades únicas por herói (só quem se arrisca merece viver o extraordinário kkk)



## 🛡️ Aviso legal (Disclaimer)

Este repositório é um projeto **não oficial** e **estritamente educacional**.  
**Epic Seven** e os nomes de personagens citados são marcas e/ou propriedades
intelectuais de seus respectivos titulares (Smilegate Megaport e/ou afiliadas).

- Este projeto **não utiliza** assets oficiais (logos, artes, sprites, áudios ou bancos de dados).
- **Não há** afiliação, endosso ou patrocínio pelos detentores da marca.
- Os nomes são usados apenas de forma **nominativa** para fins didáticos (POO em Java) e por ser fã do jogo.

Se algum titular entender que algo aqui viola seus direitos, por favor **abra uma issue**
neste repositório ou entre em contato, e **removerei prontamente**.

## **Contato:**  
GitHub: https://github.com/reginaldojr-dev  
LinkedIn: https://www.linkedin.com/in/reginaldo-junior-175148188/
