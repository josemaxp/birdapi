# Cuándo y dónde ver cada ave en España

*English below.*

Un fichero por especie del catálogo de BirdPedy (`especies/<id>.json`, con el `id` de la app). Para cada provincia
dice en cuántos años de 2020 a 2024 hay registros en cada mes, la frecuencia relativa, los mejores meses y los
mejores puntos, cuántos años y días hay registros de cada fuente, y da el mapa de distribución en cuadrículas de
10 km. Lo leen la app y la web de BirdPedy. `indice.json` dice de qué descargas sale todo, con qué licencias y qué
especie es cada fichero.

## Cómo citarlo

Los meses, los puntos y el mapa:

> GBIF.org (23 October 2026) GBIF Occurrence Download https://doi.org/10.15468/dl.r5sf2a

Los años y días de cada fuente (`cuanto`):

> GBIF.org (28 October 2026) GBIF Occurrence Download https://doi.org/10.15468/dl.aq3aan

Registros de 2020 a 2024, descargados de GBIF el 23/10/2026 y el
28/10/2026, y publicados aquí el 28/10/2026.

## Licencia

[CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/), con el texto completo en `LICENSE`. Es la de las descargas de GBIF, que toman la más restrictiva de sus
fuentes. Se pueden reutilizar sin fines comerciales, citando las descargas de arriba y diciendo si se han cambiado.

El municipio de cada punto (`municipio`) sale de los límites municipales del IGN, con licencia CC BY 4.0:
Obra derivada de BDLJE 2026 CC-BY 4.0 ign.es.

## Fuentes

| Fuente | Conjunto de datos | Editor | Licencia | DOI | Registros |
|---|---|---|---|---|---|
| Observation.org | Observation.org, Nature data from around the World | Observation.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/5nilie](https://doi.org/10.15468/5nilie) | 1.023.627 |
| eBird | EOD – eBird Observation Dataset | Cornell Lab of Ornithology | [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/) | [10.15468/aomfnb](https://doi.org/10.15468/aomfnb) | 23.873.171 |
| iNaturalist | iNaturalist Research-grade Observations | iNaturalist.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/ab3s5x](https://doi.org/10.15468/ab3s5x) | 320.635 |
| ICO | Data collected on citizen science web portal www.ornitho.cat | ICO-Institut Català d'Ornitologia (Catalan Ornithological Institute) | [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/) | [10.15470/dijq3a](https://doi.org/10.15470/dijq3a) | 321.176 |

Registros: los de cada conjunto en la descarga de los meses. Cada punto dice de qué fuente es (`fuente`) y `cuanto`
va por fuente; los meses y el mapa suman las cuatro.

## Qué hay y qué no

- Solo agregados: ninguna fecha, observador, identificador ni coordenada de un registro.
- Cada punto es el centro de una cuadrícula de 1 km de la rejilla europea (EEA, ETRS89-LAEA) con registros de al
  menos 2 días distintos, y solo en los meses en que el ave es regular en la provincia (registros en 3
  de los 5 años). Los de eBird son el lugar de una lista de eBird, que puede ser el inicio de un recorrido:
  no son un punto exacto.
- Nada de iNaturalist con la ubicación difuminada, y solo registros con una incertidumbre de 10 km o menos (los
  puntos, de 1 km o menos). La consulta a GBIF entera está en `indice.json` (`consulta`).
- También cuentan los registros que GBIF aún no casa con ninguna especie (a la mayoría los lleva solo al género) si la
  fuente los da con un nombre que en el catálogo es de una sola especie (*Thinornis dubius*, el nombre que da eBird al
  chorlitejo chico). La lista está en `indice.json` (`sin_clave`). Los que son solo de un género, los híbridos y
  los "A / B" no cuentan.

## Formato

```
{"id": 154, "especie": "Merops apiaster", "gbif": [2475443], "periodo": [2020, 2024],
 "fuentes": ["observation", "ebird", "inaturalist", "ico"], "doi": "10.15468/dl.r5sf2a",
 "provincias": {"ESP.8.1_1": {"nombre": "Madrid",
                              "meses": [[años, frecuencia], ... 12, de enero a diciembre],
                              "mejores": [8, 7, 5],
                              "puntos": {"8": [{"lat": 40.1748, "lon": -3.5786, "anos": 5, "dias": 71,
                                                "fuente": "ebird", "municipio": "Ciempozuelos"}, ...]},
                              "cuanto": {"observation": [años, días], "ebird": [años, días], ...}}},
 "distribucion": {"10kmE346N202": [años con registros en cada mes, 12]},
 "doi_cuanto": "10.15468/dl.aq3aan"}
```

- Provincias por su código GADM (`level2gid`) y su nombre en inglés, como las da GBIF.
- `frecuencia`: registros del ave entre registros de todas las aves en esa provincia y mes.
- `mejores`: hasta 3 meses regulares, de más a menos frecuencia.
- `puntos`: hasta 3 por mes regular, por más años, luego más días y luego más registros.
- `municipio`: el municipio del IGN en que cae el centro de la cuadrícula (si cae en otra provincia o en el mar, el
  de un punto de la cuadrícula a 500 m que sea de la provincia). No viene en los puntos del todo mar adentro. No es el
  nombre del lugar de eBird, que lo pone cada observador y a veces es la dirección de una casa.
- `distribucion`: cuadrículas de 10 km de la rejilla europea; el centro se calcula con EPSG:3035.
- `cuanto`: por fuente, en cuántos años de 2020 a 2024 hay registros del ave en la provincia y cuántos días distintos,
  sumados mes a mes. Solo las fuentes con registros. Sale de la descarga `doi_cuanto`, pedida otro día que la de
  los meses: GBIF recibe registros de años pasados y las dos pueden no cuadrar del todo.

---

# When and where to see each bird in Spain

One file per species in the BirdPedy catalogue (`especies/<id>.json`, with the app's `id`). For each province it
gives in how many years from 2020 to 2024 there are records in each month, the relative frequency, the best months
and the best spots, in how many years and on how many days each source has records, plus a distribution map in
10 km squares. The BirdPedy app and website read them. `indice.json` says which downloads everything comes from,
under which licences, and which species each file is.

## How to cite

Months, spots and the map:

> GBIF.org (23 October 2026) GBIF Occurrence Download https://doi.org/10.15468/dl.r5sf2a

Years and days of each source (`cuanto`):

> GBIF.org (28 October 2026) GBIF Occurrence Download https://doi.org/10.15468/dl.aq3aan

Records from 2020 to 2024, downloaded from GBIF on 23 October 2026 and
28 October 2026, and published here on 28 October 2026.

## Licence

[CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/), full text in `LICENSE`. It is the licence of the GBIF downloads, which take the most restrictive of
their sources. You may reuse the files for non-commercial purposes, citing the downloads above and stating any
changes.

The municipality of each spot (`municipio`) comes from the IGN municipal boundaries, under CC BY 4.0:
Obra derivada de BDLJE 2026 CC-BY 4.0 ign.es.

## Sources

| Source | Dataset | Publisher | Licence | DOI | Records |
|---|---|---|---|---|---|
| Observation.org | Observation.org, Nature data from around the World | Observation.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/5nilie](https://doi.org/10.15468/5nilie) | 1,023,627 |
| eBird | EOD – eBird Observation Dataset | Cornell Lab of Ornithology | [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/) | [10.15468/aomfnb](https://doi.org/10.15468/aomfnb) | 23,873,171 |
| iNaturalist | iNaturalist Research-grade Observations | iNaturalist.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/ab3s5x](https://doi.org/10.15468/ab3s5x) | 320,635 |
| ICO | Data collected on citizen science web portal www.ornitho.cat | ICO-Institut Català d'Ornitologia (Catalan Ornithological Institute) | [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/) | [10.15470/dijq3a](https://doi.org/10.15470/dijq3a) | 321,176 |

Records: those of each dataset in the months download. Each spot says which source it comes from (`fuente`) and
`cuanto` goes by source; months and the map add up all four.

## What is included and what is not

- Aggregates only: no date, observer, identifier or coordinate of any record.
- Each spot is the centre of a 1 km square of the European grid (EEA, ETRS89-LAEA) with records on at least
  2 different days, and only in months when the bird is regular in the province (records in 3 of the
  5 years). eBird spots are an eBird checklist location, which may be the start of a route: they are not an
  exact point.
- Nothing from iNaturalist with an obscured location, and only records with an uncertainty of 10 km or less (1 km or
  less for spots). The full GBIF query is in `indice.json` (`consulta`).
- Records that GBIF does not yet match to any species (most of them only to their genus) also count when the source
  gives them a name that belongs to a single catalogue species (*Thinornis dubius*, eBird's name for
  the little ringed plover). The list is in `indice.json` (`sin_clave`). Genus-only records, hybrids and "A / B"
  records do not count.

The format is described above, in Spanish: field names are the same. `cuanto` gives, for each source, in how many
years from 2020 to 2024 the bird has records in the province and on how many different days, added up month by month.
It comes from the `doi_cuanto` download, made on a different day from the months one: GBIF keeps receiving records
from past years, so the two may not match exactly. `municipio` is the IGN municipality where the centre of the
square lies (if it lies in another province or at sea, that of a point of the square 500 m away that belongs to the
province); spots far out at sea have none. It is not the eBird location name, which each observer types and is
sometimes a home address.
