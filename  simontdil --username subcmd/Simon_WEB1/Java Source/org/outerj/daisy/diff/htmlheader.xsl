<?xml version="1.0"?>
<!--
  Copyright 2004 Guy Van den Broeck

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
<html><body><xsl:apply-templates select="diffreport/diff/node()"/></body></html>
</xsl:template>

<xsl:template match="@*|node()">
<xsl:copy>
  <xsl:apply-templates select="@*|node()"/>
</xsl:copy>
</xsl:template>

<xsl:template match="img">
<img>
  <xsl:copy-of select="@*"/>
  <xsl:if test="@changeType='diff-removed-image' or @changeType='diff-added-image'">
        <xsl:attribute name="onLoad">updateOverlays()</xsl:attribute>
        <xsl:attribute name="onError">updateOverlays()</xsl:attribute>
        <xsl:attribute name="onAbort">updateOverlays()</xsl:attribute>
  </xsl:if>

</img>
</xsl:template>

<xsl:template match="span[@class='diff-html-changed']">[cambio:estilo]<xsl:copy-of select="@*"/>
  <!--xsl:attribute name="onclick">return tipC(constructToolTipC(this));</xsl:attribute-->
  <xsl:apply-templates select="node()"/>[/cambio:estilo]</xsl:template>

<xsl:template match="span[@class='diff-html-added']">[cambio:agregado]<xsl:copy-of select="@*"/>
  <!--xsl:attribute name="onclick">return tipA(constructToolTipA(this));</xsl:attribute-->
  <xsl:apply-templates select="node()"/>[/cambio:agregado]</xsl:template>

<xsl:template match="span[@class='diff-html-removed']">[cambio:removido]<xsl:copy-of select="@*"/>
  <!--xsl:attribute name="onclick">return tipR(constructToolTipR(this));</xsl:attribute-->
  <xsl:apply-templates select="node()"/>[/cambio:removido]</xsl:template>

</xsl:stylesheet>
