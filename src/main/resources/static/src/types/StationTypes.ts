export interface IStationSectionProps {

}

export interface IStationSectionState {
    stations: IStation[]
    maps: any,
    tooltipX: number,
    tooltipY: number
}

export interface IStation {
    id: number;
    description: string;
    createdAt: string;
    modifiedAt: string;
    etds: IEtd[];
    name: string;
    abbr: string;
}

export interface IEtd {
    destination: string;
    abbreviation: string;
    limited: number;
    estimates: IEstimate[];
}

export interface IEstimate {
    minutes: number;
    platform: number;
    direction: string;
    length: number;
    bikeflag: boolean;
    delay: number;
}