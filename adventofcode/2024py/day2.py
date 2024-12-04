from enum import Enum
from idlelib.debugobj import ObjectTreeItem
from wsgiref.util import request_uri

print(1)

report = '7 6 4 2 1'
levels = report.split(' ')
print(levels)
levels = list(map(int, levels))
print(levels)


class Direction(Enum):
    UP = 1
    DOWN = 2


def direction(report):
    if report[1] - report[0] >= 0:
        return Direction.UP

    return Direction.DOWN


print(direction(levels))


# given a report with gradual, increasing levels

class Report:
    def __init__(self, levels):
        self.levels = levels


#   when we validate the report
#   then we should label it safe

def is_report_safe(report):
    levels = levels(report)
    pass
