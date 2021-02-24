
import pandas as pd
import numpy as np
from sklearn import linear_model


def grade(marks):
    if marks > 90:
        return "This course seems easy for you"
    elif marks > 80:
        return "An average amount of work can get you through"
    elif marks > 65:
        return "It is going to be a difficult course for you"
    elif marks > 50:
        return "You need to work very hard in this course"
    else:
        return "You should not pick this course right now"


print("Running Python...")

df = pd.read_csv("/home/hasan/python/prac.csv")
# course = 'software engineering'
df = df.dropna(how='any',axis=0)    #remove nulls

courses = df.Courses.unique()

features = ["Pure Math","Database","Presentation","Stats",
"Design","Logic","Science","Humanities"]

model = {}
file = open("/home/hasan/python/weights.txt", "r")
file_2 = open("/home/hasan/python/results.txt", "w")

array = file.read()

# array = str(array)
array = (array.split(','))
ratings = []
for element in array:
    ratings.append(int(element))
# print(array)

for course in courses:
    temp_df = df[df['Courses'] == course]
    reg_model = linear_model.LinearRegression()
    reg_model.fit(temp_df[features],temp_df.Marks)
    # print(course, "|",reg_model.predict([ratings]))
    model[course]=reg_model
    predicted = int(reg_model.predict([ratings]))
    remarks = grade(predicted)
    file_2.write(course)
    file_2.write("\n")
    file_2.write(remarks)
    file_2.write("\n\n")

    

file.close()
file_2.close()
print("Results Stored.", )

