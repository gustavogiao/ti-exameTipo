<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/agendamentos">
        <html lang="pt">
        <head>
            <meta charset="UTF-8"/>
            <title>VetSaude - Agenda</title>
            <style>
                * {
                    box-sizing: border-box;
                }

                body {
                    margin: 0;
                    color: #20201d;
                    background: #f4f0e8;
                    font-family: Georgia, 'Times New Roman', serif;
                }

                .sheet {
                    width: min(1080px, calc(100% - 36px));
                    margin: 34px auto;
                    padding: 34px;
                    border: 1px solid #d8d0c2;
                    background: #fffaf1;
                    box-shadow: 12px 12px 0 #22221f;
                }

                header {
                    display: grid;
                    grid-template-columns: 1fr auto;
                    gap: 24px;
                    align-items: end;
                    padding-bottom: 28px;
                    border-bottom: 2px solid #22221f;
                }

                .brand {
                    margin: 0 0 12px;
                    color: #0f766e;
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 12px;
                    font-weight: 800;
                    letter-spacing: 0.18em;
                    text-transform: uppercase;
                }

                h1 {
                    margin: 0;
                    max-width: 740px;
                    font-size: clamp(42px, 7vw, 84px);
                    font-weight: 400;
                    line-height: 0.92;
                    letter-spacing: -0.06em;
                }

                .counter {
                    min-width: 154px;
                    padding: 18px 20px;
                    border: 2px solid #22221f;
                    background: #d9f99d;
                    text-align: center;
                    font-family: Arial, Helvetica, sans-serif;
                    box-shadow: 6px 6px 0 #22221f;
                }

                .counter strong {
                    display: block;
                    font-size: 44px;
                    line-height: 1;
                }

                .counter span {
                    display: block;
                    margin-top: 6px;
                    font-size: 11px;
                    font-weight: 800;
                    letter-spacing: 0.12em;
                    text-transform: uppercase;
                }

                .intro {
                    display: grid;
                    grid-template-columns: 1.4fr 0.6fr;
                    gap: 28px;
                    margin: 28px 0 24px;
                    color: #545048;
                    font-size: 18px;
                    line-height: 1.55;
                }

                .legend {
                    display: flex;
                    flex-wrap: wrap;
                    gap: 8px;
                    align-content: start;
                    justify-content: flex-end;
                    font-family: Arial, Helvetica, sans-serif;
                }

                .pill {
                    display: inline-flex;
                    align-items: center;
                    min-height: 30px;
                    padding: 6px 11px;
                    border: 1px solid #22221f;
                    border-radius: 999px;
                    background: white;
                    font-size: 12px;
                    font-weight: 800;
                }

                .pill.confirmada {
                    background: #bbf7d0;
                }

                .pill.pendente {
                    background: #fde68a;
                }

                .agenda {
                    display: grid;
                    gap: 14px;
                    margin-top: 28px;
                }

                .appointment {
                    display: grid;
                    grid-template-columns: 150px 1.1fr 1fr 170px;
                    gap: 22px;
                    align-items: stretch;
                    padding: 18px;
                    border: 1px solid #d8d0c2;
                    background: #fffdf8;
                }

                .appointment:nth-child(even) {
                    background: #f8f4ea;
                }

                .datebox {
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    padding: 14px;
                    border-left: 6px solid #0f766e;
                    background: #ecfdf5;
                    font-family: Arial, Helvetica, sans-serif;
                }

                .datebox .date {
                    font-size: 20px;
                    font-weight: 900;
                    line-height: 1.1;
                }

                .datebox .time {
                    margin-top: 8px;
                    color: #0f766e;
                    font-size: 28px;
                    font-weight: 900;
                    letter-spacing: -0.04em;
                }

                .label {
                    margin: 0 0 7px;
                    color: #80776a;
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 11px;
                    font-weight: 900;
                    letter-spacing: 0.14em;
                    text-transform: uppercase;
                }

                .name {
                    margin: 0;
                    font-size: 25px;
                    line-height: 1.05;
                    letter-spacing: -0.03em;
                }

                .meta {
                    margin: 7px 0 0;
                    color: #5d584f;
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 13px;
                    line-height: 1.45;
                }

                .microchip {
                    display: inline-block;
                    margin-top: 10px;
                    padding-top: 6px;
                    border-top: 1px solid #d8d0c2;
                    color: #80776a;
                    font-family: 'Courier New', monospace;
                    font-size: 12px;
                }

                .type {
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 16px;
                    font-weight: 900;
                    line-height: 1.3;
                }

                .status {
                    display: flex;
                    align-items: center;
                    justify-content: flex-end;
                }

                .stamp {
                    display: inline-flex;
                    justify-content: center;
                    min-width: 122px;
                    padding: 9px 12px;
                    border: 2px solid #22221f;
                    border-radius: 0;
                    transform: rotate(-2deg);
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 12px;
                    font-weight: 900;
                    letter-spacing: 0.08em;
                    text-transform: uppercase;
                    box-shadow: 4px 4px 0 #22221f;
                }

                .stamp.confirmada {
                    background: #bbf7d0;
                }

                .stamp.pendente {
                    background: #fde68a;
                }

                footer {
                    margin-top: 30px;
                    padding-top: 18px;
                    border-top: 1px solid #d8d0c2;
                    color: #80776a;
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 12px;
                    text-align: right;
                }

                @media (max-width: 860px) {
                    .sheet {
                        width: calc(100% - 24px);
                        margin: 18px auto;
                        padding: 22px;
                        box-shadow: 7px 7px 0 #22221f;
                    }

                    header,
                    .intro,
                    .appointment {
                        grid-template-columns: 1fr;
                    }

                    .legend,
                    .status {
                        justify-content: flex-start;
                    }

                    .datebox {
                        border-left: 0;
                        border-top: 6px solid #0f766e;
                    }
                }
            </style>
        </head>
        <body>
            <main class="sheet">
                <header>
                    <div>
                        <p class="brand">VetSaude / Agenda Clinica</p>
                        <h1>Consultas veterinarias</h1>
                    </div>
                    <div class="counter">
                        <strong><xsl:value-of select="count(agendamento)"/></strong>
                        <span>registos</span>
                    </div>
                </header>

                <section class="intro">
                    <p>Lista operacional de agendamentos para animais de companhia, gerada diretamente a partir de XML com uma folha XSLT. O numero de consultas pode variar sem alterar a transformacao.</p>
                    <div class="legend">
                        <span class="pill confirmada"><xsl:value-of select="count(agendamento[consulta/estado='Confirmada'])"/> confirmadas</span>
                        <span class="pill pendente"><xsl:value-of select="count(agendamento[consulta/estado='Pendente'])"/> pendentes</span>
                    </div>
                </section>

                <section class="agenda">
                    <xsl:for-each select="agendamento">
                        <xsl:sort select="consulta/data"/>
                        <xsl:sort select="consulta/hora"/>
                        <article class="appointment">
                            <div class="datebox">
                                <span class="date"><xsl:value-of select="consulta/data"/></span>
                                <span class="time"><xsl:value-of select="consulta/hora"/></span>
                            </div>

                            <div>
                                <p class="label">Animal</p>
                                <h2 class="name"><xsl:value-of select="animal/nome"/></h2>
                                <p class="meta"><xsl:value-of select="animal/especie"/> / <xsl:value-of select="animal/raca"/></p>
                                <span class="microchip">chip <xsl:value-of select="animal/microchip"/></span>
                            </div>

                            <div>
                                <p class="label">Tutor e consulta</p>
                                <h2 class="name"><xsl:value-of select="tutor/nome"/></h2>
                                <p class="meta">Telefone <xsl:value-of select="tutor/telefone"/></p>
                                <p class="type"><xsl:value-of select="consulta/tipo"/></p>
                            </div>

                            <div class="status">
                                <xsl:choose>
                                    <xsl:when test="consulta/estado='Confirmada'">
                                        <span class="stamp confirmada"><xsl:value-of select="consulta/estado"/></span>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <span class="stamp pendente"><xsl:value-of select="consulta/estado"/></span>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </div>
                        </article>
                    </xsl:for-each>
                </section>

                <footer>Transformacao XSLT aplicada a `exercicio4.xml`.</footer>
            </main>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
