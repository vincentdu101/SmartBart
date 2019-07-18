export interface IDropdownInfoState {
    dropdownOpen: boolean
}

export interface IDropdownInfoProp {
    input: any[],
    label: string,
    selectionCallback: Function
}