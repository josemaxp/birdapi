# Cuándo y dónde ver cada ave en España

*English below.*

Un fichero por especie del catálogo de BirdPedy (`especies/<id>.json`, con el `id` de la app). Para cada provincia
dice en cuántos años de 2020 a 2024 hay registros en cada mes, la frecuencia relativa, los mejores meses y los
mejores puntos, y da el mapa de distribución en cuadrículas de 10 km. Lo leen la app y la web de BirdPedy.
`indice.json` dice de qué descarga sale todo, con qué licencias y qué especie es cada fichero.

## Cómo citarlo

> GBIF.org (21 October 2026) GBIF Occurrence Download https://doi.org/10.15468/dl.b94gbm

Registros de 2020 a 2024, descargados de GBIF el 21/10/2026 y publicados aquí el
22/10/2026.

## Licencia

[CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/), con el texto completo en `LICENSE`. Es la de la descarga de GBIF, que toma la más restrictiva de sus
fuentes. Se pueden reutilizar sin fines comerciales, citando la descarga de arriba y diciendo si se han cambiado.

## Fuentes

| Fuente | Conjunto de datos | Editor | Licencia | DOI | Registros |
|---|---|---|---|---|---|
| Observation.org | Observation.org, Nature data from around the World | Observation.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/5nilie](https://doi.org/10.15468/5nilie) | 1.013.125 |
| eBird | EOD – eBird Observation Dataset | Cornell Lab of Ornithology | [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/) | [10.15468/aomfnb](https://doi.org/10.15468/aomfnb) | 23.693.991 |
| iNaturalist | iNaturalist Research-grade Observations | iNaturalist.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/ab3s5x](https://doi.org/10.15468/ab3s5x) | 318.071 |
| ICO | Data collected on citizen science web portal www.ornitho.cat | ICO-Institut Català d'Ornitologia (Catalan Ornithological Institute) | [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/) | [10.15470/dijq3a](https://doi.org/10.15470/dijq3a) | 313.512 |

Registros: los de cada conjunto en la descarga. Cada punto dice de qué fuente es (`fuente`); los meses y el mapa
suman las cuatro.

## Qué hay y qué no

- Solo agregados: ninguna fecha, observador, identificador ni coordenada de un registro.
- Cada punto es el centro de una cuadrícula de 1 km de la rejilla europea (EEA, ETRS89-LAEA) con registros de al
  menos 2 días distintos, y solo en los meses en que el ave es regular en la provincia (registros en 3
  de los 5 años). Los de eBird son el lugar de una lista de eBird, que puede ser el inicio de un recorrido:
  no son un punto exacto.
- Nada de iNaturalist con la ubicación difuminada, y solo registros con una incertidumbre de 10 km o menos (los
  puntos, de 1 km o menos). La consulta a GBIF entera está en `indice.json` (`consulta`).

## Formato

```
{"id": 154, "especie": "Merops apiaster", "gbif": [2475443], "periodo": [2020, 2024],
 "fuentes": ["observation", "ebird", "inaturalist", "ico"], "doi": "10.15468/dl.b94gbm",
 "provincias": {"ESP.8.1_1": {"nombre": "Madrid",
                              "meses": [[años, frecuencia], ... 12, de enero a diciembre],
                              "mejores": [8, 7, 5],
                              "puntos": {"8": [{"lat": 40.1748, "lon": -3.5786, "anos": 5, "dias": 71,
                                                "fuente": "ebird"}, ...]}}},
 "distribucion": {"10kmE346N202": [años con registros en cada mes, 12]}}
```

- Provincias por su código GADM (`level2gid`) y su nombre en inglés, como las da GBIF.
- `frecuencia`: registros del ave entre registros de todas las aves en esa provincia y mes.
- `mejores`: hasta 3 meses regulares, de más a menos frecuencia.
- `puntos`: hasta 3 por mes regular, por más años, luego más días y luego más registros.
- `distribucion`: cuadrículas de 10 km de la rejilla europea; el centro se calcula con EPSG:3035.

---

# When and where to see each bird in Spain

One file per species in the BirdPedy catalogue (`especies/<id>.json`, with the app's `id`). For each province it
gives in how many years from 2020 to 2024 there are records in each month, the relative frequency, the best months
and the best spots, plus a distribution map in 10 km squares. The BirdPedy app and website read them.
`indice.json` says which download everything comes from, under which licences, and which species each file is.

## How to cite

> GBIF.org (21 October 2026) GBIF Occurrence Download https://doi.org/10.15468/dl.b94gbm

Records from 2020 to 2024, downloaded from GBIF on 21 October 2026 and published here on
22 October 2026.

## Licence

[CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/), full text in `LICENSE`. It is the licence of the GBIF download, which takes the most restrictive of its
sources. You may reuse the files for non-commercial purposes, citing the download above and stating any changes.

## Sources

| Source | Dataset | Publisher | Licence | DOI | Records |
|---|---|---|---|---|---|
| Observation.org | Observation.org, Nature data from around the World | Observation.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/5nilie](https://doi.org/10.15468/5nilie) | 1,013,125 |
| eBird | EOD – eBird Observation Dataset | Cornell Lab of Ornithology | [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/) | [10.15468/aomfnb](https://doi.org/10.15468/aomfnb) | 23,693,991 |
| iNaturalist | iNaturalist Research-grade Observations | iNaturalist.org | [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) | [10.15468/ab3s5x](https://doi.org/10.15468/ab3s5x) | 318,071 |
| ICO | Data collected on citizen science web portal www.ornitho.cat | ICO-Institut Català d'Ornitologia (Catalan Ornithological Institute) | [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/) | [10.15470/dijq3a](https://doi.org/10.15470/dijq3a) | 313,512 |

Records: those of each dataset in the download. Each spot says which source it comes from (`fuente`); months and the
map add up all four.

## What is included and what is not

- Aggregates only: no date, observer, identifier or coordinate of any record.
- Each spot is the centre of a 1 km square of the European grid (EEA, ETRS89-LAEA) with records on at least
  2 different days, and only in months when the bird is regular in the province (records in 3 of the
  5 years). eBird spots are an eBird checklist location, which may be the start of a route: they are not an
  exact point.
- Nothing from iNaturalist with an obscured location, and only records with an uncertainty of 10 km or less (1 km or
  less for spots). The full GBIF query is in `indice.json` (`consulta`).

The format is described above, in Spanish: field names are the same.
