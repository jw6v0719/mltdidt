import pandas as pd
import random

df = pd.read_csv('gene_expression_with_missing_values.csv', delimiter=',')

# NOTE: this fills the same random value at all NaNs. 
df_a = df.fillna(value=random.uniform(0,1))
df_a.to_csv('replaced_with_random.csv', index=False)

df_b = df.fillna(value=df.mean(skipna=True))
df_b.to_csv('replaced_with_average.csv', index=False)

df_c = df.fillna(value=df.median(skipna=True))
df_c.to_csv('replaced_with_median.csv', index=False)

# print("# Missing values before filling:",(df.isnull().sum().sum()))
# print("# Missing values after filling:",(df_a.isnull().sum().sum()))
