# RDBMS
The third AI programming assignment.

## result
```
Load table "tools" from tools.csv
Load table "tools1" from tools1.csv
Load table "tools2" from tools2.csv
Load table "tools3" from tools3.csv
Load table "source" from source.csv
Load table "properties" from properties.csv
Instructions:
  load "filename": Load table from CSV file(without file extentions)
  save "table"[ as "filename"]: Save table to CSV file
  select "table" with "field" = "value" [and ...]: Execute SELECT
  project "table" over "field"[ and ...]: Execute PROJECT
  join "table1" and "table2" with "field" = "value" [and ...]: Execute JOIN
  show: Show names of every table
  help: Show this command information
  quit: Quit this program

You can use [select ...] or [project ...] or [join ...] as "table".

> select tools
[select, tools]
Print table "tools"
          class |          color |           size |         weight |       location |
-------------------------------------------------------------------------------------
         string |         string |         string |         string |         string |
-------------------------------------------------------------------------------------
            Saw |          Black |         Medium |          Heavy |       Pegboard |
         Hammer |           Blue |          Large |          Heavy |      Workbench |
         Pliers |           Gray |          Small |          Light |       Pegboard |
         Pliers |           Gray |         Medium |          Light |       Pegboard |
         Wrench |           Gray |          Small |          Light |       Pegboard |
         Wrench |           Gray |          Large |          Heavy |       Pegboard |

> select tools with class = Wrench and weight = Heavy
[select, tools, with, class, =, Wrench, and, weight, =, Heavy]
Print table "selected"
          class |          color |           size |         weight |       location |
-------------------------------------------------------------------------------------
         string |         string |         string |         string |         string |
-------------------------------------------------------------------------------------
         Wrench |           Gray |          Large |          Heavy |       Pegboard |

> project tools over color and weight
[project, tools, over, color, and, weight]
fields:[color, weight]
[color, weight]
Print table "projected"
          color |         weight |
----------------------------------
         string |         string |
----------------------------------
          Black |          Heavy |
           Blue |          Heavy |
           Gray |          Light |
           Gray |          Light |
           Gray |          Light |
           Gray |          Heavy |

> project [select tools class = Wrench] over color and weight
[project, TABLE, over, color, and, weight]
[select, tools, class, =, Wrench]
Print table "selected"
          class |          color |           size |         weight |       location |
-------------------------------------------------------------------------------------
         string |         string |         string |         string |         string |
-------------------------------------------------------------------------------------
         Wrench |           Gray |          Small |          Light |       Pegboard |
         Wrench |           Gray |          Large |          Heavy |       Pegboard |

fields:[color, weight]
[color, weight]
Print table "projected"
          color |         weight |
----------------------------------
         string |         string |
----------------------------------
           Gray |          Light |
           Gray |          Heavy |

> join source and properties with name = name
[join, source, and, properties, with, name, =, name]
Print table "joined"
    name.source |       retailer |name.properties |       property |
--------------------------------------------------------------------
         string |         string |         string |         string |
--------------------------------------------------------------------
           Saw8 |     Vanderhoof |           Saw8 |            Saw |
           Saw8 |     Vanderhoof |           Saw8 |          Black |
           Saw8 |     Vanderhoof |           Saw8 |         Medium |
           Saw8 |     Vanderhoof |           Saw8 |    Inexpensive |
        Hammer4 |          Sears |        Hammer4 |         Hammer |
        Hammer4 |          Sears |        Hammer4 |           Blue |
        Hammer4 |          Sears |        Hammer4 |          Large |
        Hammer4 |          Sears |        Hammer4 |      Expensive |

> join tools1 and tools3 with identifier = name and size = size
[join, tools1, and, tools3, with, identifier, =, name, and, size, =, size]
Print table "joined"
     identifier |          class |          color |    size.tools1 |         weight |           name |       location |    size.tools3 |
----------------------------------------------------------------------------------------------------------------------------------------
         string |         string |         string |         string |         string |         string |         string |         string |
----------------------------------------------------------------------------------------------------------------------------------------
           Saw8 |            Saw |          Black |         Medium |          Heavy |           Saw8 |       Pegboard |         Medium |
        Hammer4 |         Hammer |           Blue |          Large |          Heavy |        Hammer4 |      Workbench |          Large |
       Wrench17 |         Wrench |           Gray |          Small |          Light |       Wrench17 |       Pegboard |          Small |
        Wrench5 |         Wrench |           Gray |          Large |          Heavy |        Wrench5 |       Pegboard |          Large |
   Screwdriver6 |    Screwdriver |           Blue |           Long |          Light |   Screwdriver6 |      Workbench |           Long |
   Screwdriver3 |    Screwdriver |          Black |           Long |          Light |   Screwdriver3 |      Toolchest |           Long |
   Screwdriver1 |    Screwdriver |            Red |           Long |          Heavy |   Screwdriver1 |      Toolchest |           Long |
   Screwdriver9 |    Screwdriver |            Red |          Short |          Light |   Screwdriver9 |      Toolchest |          Short |

> join [select source with name = Saw8] and [select properties with Property = Black or Property = Medium] with name = name
[join, TABLE, and, TABLE, with, name, =, name]
[select, source, with, name, =, Saw8]
Print table "selected"
           name |       retailer |
----------------------------------
         string |         string |
----------------------------------
           Saw8 |     Vanderhoof |

[select, properties, with, Property, =, Black, or, Property, =, Medium]
Print table "selected"
           name |       property |
----------------------------------
         string |         string |
----------------------------------
           Saw8 |          Black |
           Saw8 |         Medium |

Print table "joined"
  name.selected |       retailer |  name.selected |       property |
--------------------------------------------------------------------
         string |         string |         string |         string |
--------------------------------------------------------------------
           Saw8 |     Vanderhoof |           Saw8 |          Black |
           Saw8 |     Vanderhoof |           Saw8 |         Medium |

> save [select tools with class = Wrench] as SelectedTools
[save, TABLE, as, SelectedTools]
[select, tools, with, class, =, Wrench]
Print table "selected"
          class |          color |           size |         weight |       location |
-------------------------------------------------------------------------------------
         string |         string |         string |         string |         string |
-------------------------------------------------------------------------------------
         Wrench |           Gray |          Small |          Light |       Pegboard |
         Wrench |           Gray |          Large |          Heavy |       Pegboard |

Save table "selected" to SelectedTools.csv
Load table "selectedtools" from selectedtools.csv
> quit
quit
```