
def unique():
    # replace your file paths!
    with open("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\good.csv") as f:
        content = f.readlines()
        with open('D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\good_unique.csv', 'w') as f2:
            for g in set(content):
                f2.write(g)

if __name__ == "__main__":
    unique()
